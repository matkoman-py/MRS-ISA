package pharmacyhub.domain;

import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

//@Entity
//@SQLDelete(sql = "UPDATE drug SET deleted = true WHERE id = ?")
//@Where(clause = "deleted = false")
public class Offer extends BaseEntity{
	

}
