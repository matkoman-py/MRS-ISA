package pharmacyhub.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.AbsenceRequest;
import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Employment;
import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.domain.enums.AbsenceRequestStatus;
import pharmacyhub.domain.enums.UserType;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.DrugstoreAdmin;
import pharmacyhub.domain.users.Employee;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.dto.DermatologistDto;
import pharmacyhub.dto.DermatologistOverviewDto;
import pharmacyhub.dto.EmployeeOverviewDto;
import pharmacyhub.dto.PharmacistAbsenceRequestDto;
import pharmacyhub.dto.PharmacistOverviewDto;
import pharmacyhub.dto.SearchDermatologistDto;
import pharmacyhub.repositories.AbsenceRequestRepository;
import pharmacyhub.repositories.DermatologistAppointmentRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.EmploymentRepository;
import pharmacyhub.repositories.LocationRepository;
import pharmacyhub.repositories.PharmacistAppointmentRepository;
import pharmacyhub.repositories.RatingDermatologistRepository;
import pharmacyhub.repositories.RatingPharmacistRepository;
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
	private UserNotificationService userNotificationService;
	
	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired
	private EmploymentRepository employmentRepository;
	
	@Autowired
	private PharmacistAppointmentRepository pharmacistAppointmentRepository;
	
	@Autowired
	private DermatologistAppointmentRepository dermatologistAppointmentRepository;
	
	@Autowired
	private AbsenceRequestRepository abensceRequestRepository;
	
	@Autowired
	private RatingDermatologistRepository ratingDermatologistRepository;
	
	@Autowired
	private RatingPharmacistRepository ratingPharmacistRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	public Collection<DermatologistDto> searchDermatologist(SearchDermatologistDto searchDermatologistDto) {

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

	public List<Employee> findAll() {
		List<Employee> pharmacists = new ArrayList<Employee>(pharmacistRepository.findAll());
		List<Employee> dermatologists = new ArrayList<Employee>(dermatologistRepository.findAll());
		return Stream.concat(pharmacists.stream(), dermatologists.stream()).collect(Collectors.toList());
	}

	/*public Pharmacist savePharmacist(Employee employee) throws Exception {

		locationRepository.save(employee.getLocation());
		Pharmacist pharmacist = new Pharmacist(employee.getEmail(), RadnomGeneratorUtil.generateEmployeePassword(),
				employee.getName(), employee.getSurname(), employee.getPhoneNumber(), employee.getLocation(),
				employee.getWorkingHoursFrom(), employee.getWorkingHoursTo());

		pharmacistRepository.save(pharmacist);
		userNotificationService.sendEmployeeInitialPassword(pharmacist.getEmail(), pharmacist.getPassword());

		return pharmacistRepository.save(pharmacist);
	}*/

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
			//savePharmacist(employee);
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
			
			derm.setLocation(locationRepository.save(employee.getLocation()));
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
			pharm.setLocation(locationRepository.save(employee.getLocation()));
			pharm.setName(employee.getName());
			pharm.setPassword(employee.getPassword());
			pharm.setPhoneNumber(employee.getPhoneNumber());
			pharm.setSurname(employee.getSurname());
			pharmacistRepository.save(pharm);

			System.out.println(pharmacistRepository.findById(employee.getId()));

		}
		return findAll();
	}
	
	public List<Employee> updatePassword(Employee employee) throws Exception {

		if (employee.getType().equals(UserType.Dermatologist)) {
			Dermatologist derm = dermatologistRepository.findById(employee.getId()).orElse(null);
			if (derm.equals(null)) {
				throw new Exception("This dermatologist does not exist!");
			}
			derm.setEmail(employee.getEmail());
			
			derm.setLocation(locationRepository.save(employee.getLocation()));
			derm.setName(employee.getName());
			derm.setPassword(passwordEncoder.encode(employee.getPassword()));
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
			pharm.setLocation(locationRepository.save(employee.getLocation()));
			pharm.setName(employee.getName());
			pharm.setPassword(passwordEncoder.encode(employee.getPassword()));
			pharm.setPhoneNumber(employee.getPhoneNumber());
			pharm.setSurname(employee.getSurname());
			pharmacistRepository.save(pharm);

			System.out.println(pharmacistRepository.findById(employee.getId()));

		}
		return findAll();
	}
	
	public Employee findOne(String employeeId) {
		if(pharmacistRepository.findById(employeeId).orElse(null) == null) {
			  Employee employee = dermatologistRepository.findById(employeeId).orElse(null);
			  return employee;
		}
		Employee employee =  pharmacistRepository.findById(employeeId).orElse(null);
		return employee;
  }
  
	public List<EmployeeOverviewDto> getAllEmployeesOfDrugstore(String adminId) {
		DrugstoreAdmin user = (DrugstoreAdmin)userRepository.findById(adminId).orElse(null);
		String drugstoreId = user.getDrugstore().getId();
		List<Pharmacist> pharmacists = pharmacistRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null));	
		List<Employment> dermatologistEmployments = employmentRepository.findByDrugstoreId(drugstoreId);		
		List<EmployeeOverviewDto> employees = new ArrayList<EmployeeOverviewDto>();
		
		for (Employee e : pharmacists) {
			
			employees.add(new EmployeeOverviewDto(e.getName(), e.getSurname(), e.getRating(), e.getEmail(), e.getPhoneNumber(), e.getLocation(), "Pharmacist"));
		}
		for (Employment e : dermatologistEmployments) {
			
			
			employees.add(new EmployeeOverviewDto(e.getDermatologist().getName(), e.getDermatologist().getSurname(), e.getDermatologist().getRating(), e.getDermatologist().getEmail(), e.getDermatologist().getPhoneNumber(), e.getDermatologist().getLocation(), "Dermatologist"));
		}
		return employees;
	}
	
	public List<EmployeeOverviewDto> getAllEmployeesOfDrugstoreBySearch(String adminId, String searchText, double minRate,
			double maxRate, String employeeType) {
		// drugstore id je zapravo drugstore admin id
		DrugstoreAdmin user = (DrugstoreAdmin)userRepository.findById(adminId).orElse(null);
		String drugstoreId = user.getDrugstore().getId();
		List<Pharmacist> pharmacists = new ArrayList<Pharmacist>();
		List<Employment> dermatologistEmployments = new ArrayList<Employment>();
		List<EmployeeOverviewDto> employees = new ArrayList<EmployeeOverviewDto>();
		if (employeeType.equals("Dermatologists")) {
			dermatologistEmployments = employmentRepository.findByDrugstoreId(drugstoreId);
		} else if (employeeType.equals("Pharmacists")) {
			pharmacists = pharmacistRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null));
		} else {
			pharmacists = pharmacistRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null));
			dermatologistEmployments = employmentRepository.findByDrugstoreId(drugstoreId);
		}
		
		for (Employee e : pharmacists) {
			if (checkIfSearchedTextIsIncluded(searchText, e)) {
				if (minRate <= e.getRating() && maxRate >= e.getRating()) { //ovo treba ispraviti
					
					employees.add(new EmployeeOverviewDto(e.getName(), e.getSurname(), e.getRating(), e.getEmail(), e.getPhoneNumber(), e.getLocation(), "Pharmacist"));
				}
			}
		}
		for (Employment e : dermatologistEmployments) {
			if (checkIfSearchedTextIsIncluded(searchText, e.getDermatologist())) {
				if (minRate <= e.getDermatologist().getRating() && maxRate >= e.getDermatologist().getRating()) { //ovo treba ispraviti
					
					employees.add(new EmployeeOverviewDto(e.getDermatologist().getName(), e.getDermatologist().getSurname(), e.getDermatologist().getRating(), e.getDermatologist().getEmail(), e.getDermatologist().getPhoneNumber(), e.getDermatologist().getLocation(), "Dermatologist"));
				}
			}
		}
		
		return employees;
	}

	boolean checkIfSearchedTextIsIncluded(String searchText, Employee e) {
		if (searchText.equals(""))
			return true;
		if (e.getPhoneNumber() != null && e.getPhoneNumber().toLowerCase().contains(searchText.toLowerCase()))
			return true;
		if (e.getName().toLowerCase().contains(searchText.toLowerCase()) || e.getSurname().toLowerCase().contains(searchText.toLowerCase()) ||
				e.getEmail().toLowerCase().contains(searchText.toLowerCase()) || e.getLocation().getAddress().toLowerCase().contains(searchText.toLowerCase()) || 
				e.getLocation().getCity().toLowerCase().contains(searchText.toLowerCase()) || e.getLocation().getCountry().toLowerCase().contains(searchText.toLowerCase()))
			return true;
		return false;
	}
	public List<PharmacistOverviewDto> getAllPharmacists() {
		List<Pharmacist> pharmacists = pharmacistRepository.findAll();
		List<PharmacistOverviewDto> pharmacistReturnValues = new ArrayList<PharmacistOverviewDto>();
		for (Pharmacist pharmacist : pharmacists) {
			pharmacistReturnValues.add(new PharmacistOverviewDto(pharmacist.getName(), pharmacist.getSurname(), pharmacist.getRating(), pharmacist.getDrugstore().getName()));
		}
		return pharmacistReturnValues;
	}

	public List<PharmacistOverviewDto> getPharmacistBySearch(String name, String surname, double minRate, double maxRate, String drugstore) {
		List<Pharmacist> pharmacists = new ArrayList<Pharmacist>();
		if (drugstore.equals("")) {
			pharmacists = pharmacistRepository.findAll();
		} else {
			List<Drugstore> drugstores = drugstoreRepository.findByName(drugstore);
			for (Drugstore d : drugstores) {
				pharmacists.addAll(pharmacistRepository.findByDrugstore(d));
			}
		}
		List<PharmacistOverviewDto> pharmacistReturnValues = new ArrayList<PharmacistOverviewDto>();
		for (Pharmacist pharmacist : pharmacists) {
			if (!name.equals("") && !name.toLowerCase().equals(pharmacist.getName().toLowerCase()))
				continue;
			else if (!surname.equals("") && !surname.toLowerCase().equals(pharmacist.getSurname().toLowerCase()))
				continue;
			else if (minRate != 0 && minRate > pharmacist.getRating())//treba ispraviti
				continue;
			else if (maxRate != 0 && maxRate < pharmacist.getRating())//treba ispraviti
				continue;
			else {
				pharmacistReturnValues.add(new PharmacistOverviewDto(pharmacist.getName(), pharmacist.getSurname(), pharmacist.getRating(), pharmacist.getDrugstore().getName()));
			}
		}
		return pharmacistReturnValues;
	}

	public List<DermatologistOverviewDto> getAllDermatologists() {
		List<Dermatologist> dermatologists = dermatologistRepository.findAll();
		List<DermatologistOverviewDto> dermatologistReturnValues = new ArrayList<DermatologistOverviewDto>();
		for (Dermatologist dermatologist : dermatologists) {
			String drugstores = "";
			List<Employment> employments = employmentRepository.findByDermatologistId(dermatologist.getId());
			for (Employment e : employments) {
				if (drugstores.equals(""))
					drugstores += e.getDrugstore().getName();
				else
					drugstores += ", " + e.getDrugstore().getName();
			}
			dermatologistReturnValues.add(new DermatologistOverviewDto(dermatologist.getName(), dermatologist.getSurname(), dermatologist.getRating(), drugstores));
		}
		return dermatologistReturnValues;
	}

	public List<DermatologistOverviewDto> getDermatologistBySearch(String name, String surname, double minRate, double maxRate,
			String drugstore) {
		List<Dermatologist> dermatologists = new ArrayList<Dermatologist>();
		if (drugstore.equals("")) {
			dermatologists = dermatologistRepository.findAll();
		} else {
			List<Employment> employments = employmentRepository.findAll();
			for (Employment e : employments) {
				if (e.getDrugstore().getName().equals(drugstore)) {
					if (!dermatologists.contains(e.getDrugstore()))
						dermatologists.add(e.getDermatologist());
				}
			}
		}
		List<DermatologistOverviewDto> dermatologistReturnValues = new ArrayList<DermatologistOverviewDto>();
		for (Dermatologist dermatologist : dermatologists) {
			if (!name.equals("") && !name.toLowerCase().equals(dermatologist.getName().toLowerCase()))
				continue;
			else if (!surname.equals("") && !surname.toLowerCase().equals(dermatologist.getSurname().toLowerCase()))
				continue;
			else if (minRate != 0 && minRate > dermatologist.getRating())//treba ispraviti
				continue;
			else if (maxRate != 0 && maxRate < dermatologist.getRating())//treba ispraviti
				continue;
			else {
				String drugstores = "";
				List<Employment> employments = employmentRepository.findByDermatologistId(dermatologist.getId());
				for (Employment e : employments) {
					if (drugstores.equals(""))
						drugstores += e.getDrugstore().getName();
					else
						drugstores += ", " + e.getDrugstore().getName();
				}
				dermatologistReturnValues.add(new DermatologistOverviewDto(dermatologist.getName(), dermatologist.getSurname(), dermatologist.getRating(), drugstores));
			}
		}
		return dermatologistReturnValues;
	}

	public Drugstore findDrugstoreByDrugstoreAdminId(String adminId) {
		DrugstoreAdmin admin = (DrugstoreAdmin)userRepository.findById(adminId).orElse(null);
		return admin.getDrugstore();
	}

	public List<Pharmacist> addPharmacist(Pharmacist pharmacist) throws Exception{
		if (userRepository.findByEmail(pharmacist.getEmail()) != null) {
			throw new Exception("A user with this email already exist!");
		}
		Drugstore dg = drugstoreRepository.findById(pharmacist.getDrugstore().getId()).orElse(null);
		locationRepository.save(pharmacist.getLocation());
		String rawPassword = RadnomGeneratorUtil.generateEmployeePassword();
		Pharmacist p = new Pharmacist(pharmacist.getEmail(), passwordEncoder.encode(rawPassword),
				pharmacist.getName(), pharmacist.getSurname(), pharmacist.getPhoneNumber(), pharmacist.getLocation(),
				pharmacist.getWorkingHoursFrom(), pharmacist.getWorkingHoursTo(), dg);

		pharmacistRepository.save(p);
		userNotificationService.sendEmployeeInitialPassword(p.getEmail(), rawPassword);
				
		return null;
	}

	public String deletePharmacist(String pharmacistEmail) {
		if (checkFuturePharmacistAppointments(pharmacistEmail)) {
			return "Denied";
		}
		pharmacistRepository.deleteByEmail(pharmacistEmail);
		return "Success";
	}

	private boolean checkFuturePharmacistAppointments(String pharmacistEmail) {
		List<PharmacistAppointment> appointments = pharmacistAppointmentRepository.findByPharmacistEmailAndProcessedFalse(pharmacistEmail);
		for (PharmacistAppointment appointment : appointments) {
			if (appointment.getDate().after(new Date())) {
				return true;
			}
		}
		return false;
		//String pharmacistId = pharmacistRepository.findByEmail(pharmacistEmail).getId();
		//pharmacistAppointmentRepository.deleteByPharmacist((Pharmacist)pharmacistRepository.findByEmail(pharmacistEmail)); //treba samo one koji predstoje?
	}
	
	
	public String deleteDermatologist(String dermatologistEmail) throws Exception {
		if (checkFutureDermatologistAppointments(dermatologistEmail)) {
			throw new Exception("Can't delete dermatologist!");
		}
		
		employmentRepository.deleteByDermatologistEmail(dermatologistEmail);
		dermatologistRepository.deleteByEmail(dermatologistEmail);
		return "Success";
	}

	private boolean checkFutureDermatologistAppointments(String dermatologistEmail) {
		List<DermatologistAppointment> appointments = dermatologistAppointmentRepository.findByDermatologistEmailAndProcessedFalse(dermatologistEmail);
		for (DermatologistAppointment appointment : appointments) {
			if (appointment.getDate().after(new Date())) {
				return true;
			}
		}
		return false;
		//String pharmacistId = pharmacistRepository.findByEmail(pharmacistEmail).getId();
		//pharmacistAppointmentRepository.deleteByPharmacist((Pharmacist)pharmacistRepository.findByEmail(pharmacistEmail)); //treba samo one koji predstoje?
	}
	
	public boolean passwordValid(String employeeId, String passwordInput) {
		
		if(pharmacistRepository.findById(employeeId).orElse(null) != null) {
			Pharmacist ph = pharmacistRepository.findById(employeeId).orElse(null);
			return passwordEncoder.matches(passwordInput, ph.getPassword());
		}else {
			Dermatologist ph = dermatologistRepository.findById(employeeId).orElse(null);
			return passwordEncoder.matches(passwordInput, ph.getPassword());
		}
	}

	public List<PharmacistAbsenceRequestDto> getPharmacistRequests(String drugstoreId) {
		List<Pharmacist> pharmacists = pharmacistRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null));	
		List<PharmacistAbsenceRequestDto> requests = new ArrayList<PharmacistAbsenceRequestDto>();
		for (Pharmacist pharmacist : pharmacists) {
			List<AbsenceRequest> requestsForCurrentPharmacist = abensceRequestRepository.findByEmployeeAndStatus(pharmacist, AbsenceRequestStatus.Pending);
			for (AbsenceRequest absenceRequest : requestsForCurrentPharmacist) {
				requests.add(new PharmacistAbsenceRequestDto(absenceRequest.getEmployee().getName(), absenceRequest.getEmployee().getSurname(), absenceRequest.getStartDate(), absenceRequest.getEndDate(), absenceRequest.getReason(), absenceRequest.getId()));
			}
		}
		return requests;
	}
}
