package pharmacyhub.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import pharmacyhub.domain.users.User;

@NoRepositoryBean
public interface AbstractUserRepository<T extends User> extends JpaRepository<T, String> {

}
