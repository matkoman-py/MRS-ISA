package pharmacyhub.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import pharmacyhub.domain.enums.UserType;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Employee;
import pharmacyhub.domain.users.Pharmacist;

@Repository
public class EmployeeRepository {

private ArrayList<Employee> employees;
	
	public EmployeeRepository() {
		employees = new ArrayList<Employee>();
		this.setDummyData();
	}
	
	public ArrayList<Employee> readAll() {
		return employees;
	}
	
	public void setDummyData() {
		Pharmacist p1 = new Pharmacist();
		Pharmacist p2 = new Pharmacist();
		Dermatologist d1 = new Dermatologist();
		Dermatologist d2 = new Dermatologist();
		Pharmacist p3 = new Pharmacist();
		
		p1.setName("Pera");
		p1.setSurname("Peric");
		p1.setType(UserType.Pharmacist);
		
		p2.setName("Mika");
		p2.setSurname("Mikic");
		p2.setType(UserType.Pharmacist);
		
		d1.setName("Djoka");
		d1.setSurname("Djokic");
		d1.setType(UserType.Dermatologist);
		
		d2.setName("Steva");
		d2.setSurname("Stevic");
		d2.setType(UserType.Dermatologist);
		
		p3.setName("Zika");
		p3.setSurname("Zikic");
		p3.setType(UserType.Pharmacist);
		
		employees.add(p1);
		employees.add(p2);
		employees.add(d1);
		employees.add(d2);
		employees.add(p3);
	}
}
