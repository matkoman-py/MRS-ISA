package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.enums.UserType;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Employee;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.repositories.LocationRepository;
import pharmacyhub.repositories.users.DermatologistRepository;
import pharmacyhub.repositories.users.PharmacistRepository;
import pharmacyhub.repositories.users.UserRepository;

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

	public List<Employee> findAll() {
		List<Employee> pharmacists = new ArrayList<Employee>(pharmacistRepository.findAll());
		List<Employee> dermatologists = new ArrayList<Employee>(dermatologistRepository.findAll());
		return Stream.concat(pharmacists.stream(), dermatologists.stream()).collect(Collectors.toList());
	}

	public Pharmacist savePharmacist(Pharmacist pharmacist) throws Exception {

		// fale provere
		return pharmacistRepository.save(pharmacist);
	}

	public Dermatologist saveDermatologist(Dermatologist dermatologist) throws Exception {

		// fale provere
		return dermatologistRepository.save(dermatologist);
	}

	public List<Employee> addEmployee(Employee employee) throws Exception {

		if (userRepository.findByEmail(employee.getEmail()) != null) {
			throw new Exception("A user with this email already exist!");
		}

		if (employee.getType().equals(UserType.Dermatologist)) {
			locationRepository.save(employee.getLocation());
			dermatologistRepository
					.save(new Dermatologist(employee.getEmail(), employee.getPassword(), employee.getName(),
							employee.getSurname(), employee.getPhoneNumber(), employee.getLocation(), false, null));
		} else {
			locationRepository.save(employee.getLocation());
			pharmacistRepository.save(new Pharmacist(employee.getEmail(), employee.getPassword(), employee.getName(),
					employee.getSurname(), employee.getPhoneNumber(), employee.getLocation(), false, null));
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

		}
		return findAll();
	}
}
