package pharmacyhub.domain.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DrugstoreAdmin")
public class DrugstoreAdmin extends User{

}
