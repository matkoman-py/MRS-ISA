package pharmacyhub.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Employement;
import pharmacyhub.domain.enums.UserType;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Employee;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.dto.AddDermatologistToDrugstoreDto;
import pharmacyhub.dto.DermatologistDto;
import pharmacyhub.dto.SearchDermatologistDto;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.EmployementRepository;
import pharmacyhub.repositories.LocationRepository;
import pharmacyhub.repositories.users.DermatologistRepository;
import pharmacyhub.repositories.users.PharmacistRepository;
import pharmacyhub.repositories.users.UserRepository;
import pharmacyhub.utils.RadnomGeneratorUtil;

@Service
public class EmployeeService {

	@Autowired
	private PharmacistRepository pharmacistRepository;

	@Autowired
	private DermatologistRepository dermatologistRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private EmployementRepository employementRepository;

	@Autowired
	private DrugstoreRepository drugstoreRepository;

	@Autowired
	private UserNotificationService userNotificationService;

	public Collection<DermatologistDto> searchDermatologist(SearchDermatologistDto searchDermatologistDto) {
		System.out.println("Name: " + searchDermatologistDto.getName());
		System.out.println("Surname: " + searchDermatologistDto.getSurname());

		List<Dermatologist> dermatologists = dermatologistRepository.findAll().stream()
				.filter(dermatologist -> isNullOrEmptyString(searchDermatologistDto.getName()) || dermatologist
						.getName().toLowerCase().contains(searchDermatologistDto.getName().toLowerCase()))
				.filter(dermatologist -> isNullOrEmptyString(searchDermatologistDto.getSurname()) || dermatologist
						.getSurname().toLowerCase().contains(searchDermatologistDto.getSurname().toLowerCase()))
				.collect(Collectors.toList());
		List<DermatologistDto> dtoList = new ArrayList<>();
		for (Dermatologist dermatologist : dermatologists) {
			dtoList.add(new DermatologistDto(dermatologist));
		}
		return dtoList;
	}

	private boolean isNullOrEmptyString(String string) {
		return string == null || string.equals("");
	}

	public DermatologistDto addDermatologistToDrugstore(AddDermatologistToDrugstoreDto requestDto) throws Exception {

		// TODO: dodati proveru za duplikate
		Employement employement = new Employement();

		Dermatologist dermatologist = (Dermatologist) dermatologistRepository
				.findByEmail(requestDto.getDermatologistEmail());
		if (dermatologist == null) {
			throw new Exception("No such dermatologist");
		}

		Drugstore drugstore = drugstoreRepository.findById(requestDto.getDrugstoreId()).orElse(null);
		if (drugstore == null) {
			throw new Exception("No such drugstore");
		}

		Employement duplicateEmployement = employementRepository
				.findByDermatologistIdAndDrugstoreId(dermatologist.getId(), drugstore.getId());
		
		if (duplicateEmployement != null) {
			throw new Exception("Dermatologist already works for pharmacy");
		}

		employement.setDermatologist(dermatologist);
		employement.setDrugstore(drugstore);
		drugstore.getEmployements().add(employement);
		dermatologist.getEmployements().add(employement);
		employement.setWorkingHoursFrom(drugstore.getWorkingHoursFrom());
		employement.setWorkingHoursTo(drugstore.getWorkingHoursTo());

		employementRepository.save(employement);
		dermatologistRepository.save(dermatologist);
		drugstoreRepository.save(drugstore);

		return new DermatologistDto(dermatologist);

	}

	public List<Employee> findAll() {
		List<Employee> pharmacists = new ArrayList<Employee>(pharmacistRepository.findAll());
		List<Employee> dermatologists = new ArrayList<Employee>(dermatologistRepository.findAll());
		return Stream.concat(pharmacists.stream(), dermatologists.stream()).collect(Collectors.toList());
	}

	public Pharmacist savePharmacist(Employee employee) throws Exception {

		locationRepository.save(employee.getLocation());
		Pharmacist pharmacist = new Pharmacist(employee.getEmail(), RadnomGeneratorUtil.generateEmployeePassword(),
				employee.getName(), employee.getSurname(), employee.getPhoneNumber(), employee.getLocation(),
				employee.getWorkingHoursFrom(), employee.getWorkingHoursTo());

		pharmacistRepository.save(pharmacist);
		userNotificationService.sendEmployeeInitialPassword(pharmacist.getEmail(), pharmacist.getPassword());

		return pharmacistRepository.save(pharmacist);
	}

	public Dermatologist saveDermatologist(Employee employee) throws Exception {

		locationRepository.save(employee.getLocation());
		Dermatologist dermatologist = new Dermatologist(employee.getEmail(),
				RadnomGeneratorUtil.generateEmployeePassword(), employee.getName(), employee.getSurname(),
				employee.getPhoneNumber(), employee.getLocation(), employee.getWorkingHoursFrom(),
				employee.getWorkingHoursTo());

		dermatologistRepository.save(dermatologist);
		userNotificationService.sendEmployeeInitialPassword(dermatologist.getEmail(), dermatologist.getPassword());

		return dermatologistRepository.save(dermatologist);
	}

	public List<Employee> addEmployee(Employee employee) throws Exception {

		if (userRepository.findByEmail(employee.getEmail()) != null) {
			throw new Exception("A user with this email already exist!");
		}

		if (employee.getType().equals(UserType.Dermatologist)) {
			saveDermatologist(employee);
		} else {
			savePharmacist(employee);
		}
		return findAll();
	}

	public List<Employee> update(Employee employee) throws Exception {

		if (employee.getType().equals(UserType.Dermatologist)) {
			Dermatologist derm = dermatologistRepository.findById(employee.getId()).orElse(null);
			if (derm.equals(null)) {
				throw new Exception("This dermatologist does not exist!");
			}
			derm.setEmail(employee.getEmail());
			derm.setLocation(employee.getLocation());
			derm.setName(employee.getName());
			derm.setPassword(employee.getPassword());
			derm.setPhoneNumber(employee.getPhoneNumber());
			derm.setSurname(employee.getSurname());
			dermatologistRepository.save(derm);

			System.out.println(dermatologistRepository.findById(employee.getId()));
		} else {
			Pharmacist pharm = pharmacistRepository.findById(employee.getId()).orElse(null);
			if (pharm.equals(null)) {
				throw new Exception("This pharmacist does not exist!");
			}

			pharm.setEmail(employee.getEmail());
			pharm.setLocation(employee.getLocation());
			pharm.setName(employee.getName());
			pharm.setPassword(employee.getPassword());
			pharm.setPhoneNumber(employee.getPhoneNumber());
			pharm.setSurname(employee.getSurname());
			pharmacistRepository.save(pharm);

			System.out.println(pharmacistRepository.findById(employee.getId()));

		}
		return findAll();
	}
}
