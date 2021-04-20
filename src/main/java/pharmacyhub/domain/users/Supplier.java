package pharmacyhub.domain.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Supplier")
public class Supplier extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
