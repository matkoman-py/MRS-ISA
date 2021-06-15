package pharmacyhub.domain.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@DiscriminatorValue("Supplier")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ? and version = ?")
@Where(clause = "deleted = false")
public class Supplier extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
