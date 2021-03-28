package pharmacyhub.repositories.users;

import javax.transaction.Transactional;

import pharmacyhub.domain.users.Dermatologist;

@Transactional
public interface DermatologistRepository extends EmployeeRepository<Dermatologist> {
}
