package pharmacyhub.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import pharmacyhub.domain.users.Employee;

@Repository
public class EmployeeRepository {

private ArrayList<Employee> employees;
	
	public EmployeeRepository() {
		employees = new ArrayList<Employee>();
	}
	
	public ArrayList<Employee> readAll() {
		return employees;
	}
}
