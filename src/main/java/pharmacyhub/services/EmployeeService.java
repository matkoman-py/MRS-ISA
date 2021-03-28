package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.enums.UserType;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Employee;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.repositories.users.DermatologistRepository;
import pharmacyhub.repositories.users.PharmacistRepository;

@Service
public class EmployeeService {

	@Autowired
	private PharmacistRepository pharmacistRepository;
	
	@Autowired
	private DermatologistRepository dermatologistRepository;

	public List<Employee> findAll() {
		List<Employee> pharmacists = new ArrayList<Employee>(pharmacistRepository.findAll());
		List<Employee> dermatologists = new ArrayList<Employee>(dermatologistRepository.findAll());
		return Stream.concat(pharmacists.stream(), dermatologists.stream()).collect(Collectors.toList());
	}
	
	public List<Employee> setDummyData() {
		Pharmacist p1 = new Pharmacist();
		Pharmacist p2 = new Pharmacist();
		Dermatologist d1 = new Dermatologist();
		Dermatologist d2 = new Dermatologist();
		Pharmacist p3 = new Pharmacist();
		
		p1.setName("Pera");
		p1.setSurname("Peric");
		p1.setPassword("123");
		p1.setEmail("pera@peric.com");
		p1.setType(UserType.Pharmacist);
		
		p2.setName("Mika");
		p2.setSurname("Mikic");
		p2.setPassword("123");
		p2.setEmail("mika@mikic.com");
		p2.setType(UserType.Pharmacist);
		
		d1.setName("Djoka");
		d1.setSurname("Djokic");
		d1.setPassword("123");
		d1.setEmail("djoka@djokic.com");
		d1.setType(UserType.Dermatologist);
		
		d2.setName("Steva");
		d2.setSurname("Stevic");
		d2.setPassword("123");
		d2.setEmail("steva@stevic.com");
		d2.setType(UserType.Dermatologist);
		
		p3.setName("Zika");
		p3.setSurname("Zikic");
		p3.setPassword("123");
		p3.setEmail("zika@zikic.com");
		p3.setType(UserType.Pharmacist);
		
		pharmacistRepository.save(p1);
		pharmacistRepository.save(p2);
		pharmacistRepository.save(p3);
		dermatologistRepository.save(d1);
		dermatologistRepository.save(d2);
		
		return findAll();

	}

	public Pharmacist savePharmacist(Pharmacist pharmacist) throws Exception {

		//fale provere
		return pharmacistRepository.save(pharmacist);
	}
	
	public Dermatologist saveDermatologist(Dermatologist dermatologist) throws Exception {

		//fale provere
		return dermatologistRepository.save(dermatologist);
	}
	
	public List<Employee> addEmployee(Employee employee) throws Exception{
		if(employee.getType().equals(UserType.Dermatologist)) {
			//deo za dodavanje dermatologa
			if (dermatologistRepository.findByEmail(employee.getEmail()) != null) {
				throw new Exception("This dermatologist with this email already exist!");
			}
			dermatologistRepository.save(new Dermatologist(employee.getEmail(), employee.getPassword(), employee.getName(), employee.getSurname(), employee.getPhoneNumber(), employee.getLocation(), employee.getType()));
		}else {
			if (pharmacistRepository.findByEmail(employee.getEmail()) != null) {
				throw new Exception("This pharmacist with this email already exist!");
			}
			pharmacistRepository.save(new Pharmacist(employee.getEmail(), employee.getPassword(), employee.getName(), employee.getSurname(), employee.getPhoneNumber(), employee.getLocation(), employee.getType()));
		}
		return findAll();
	}
}
