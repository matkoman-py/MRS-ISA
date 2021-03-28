package pharmacyhub.repositories.users;

import org.springframework.data.repository.NoRepositoryBean;

import pharmacyhub.domain.users.Employee;

@NoRepositoryBean
public interface EmployeeRepository<T extends Employee> extends AbstractUserRepository<T>{
	//ovde idu metode koje su zajednicke za farmaceuta i dermatologa
	Employee findByEmail(String email);
}
