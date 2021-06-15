package pharmacyhub.domain.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@DiscriminatorValue("SystemAdmin")
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id = ? and version = ?")
@Where(clause = "deleted = false")
public class SystemAdmin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
