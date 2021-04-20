package pharmacyhub.domain.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SystemAdmin")
public class SystemAdmin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
