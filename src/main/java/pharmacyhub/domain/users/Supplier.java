package pharmacyhub.domain.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Supplier")
public class Supplier extends User{

}
