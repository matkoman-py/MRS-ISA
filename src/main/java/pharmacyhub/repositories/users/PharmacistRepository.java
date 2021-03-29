package pharmacyhub.repositories.users;

import javax.transaction.Transactional;

import pharmacyhub.domain.users.Pharmacist;

@Transactional
public interface PharmacistRepository extends EmployeeRepository<Pharmacist> {

}
