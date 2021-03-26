package pharmacyhub.repositories.users;

import javax.transaction.Transactional;

import pharmacyhub.domain.users.User;

@Transactional
public interface UserRepository extends AbstractUserRepository<User> {

}
