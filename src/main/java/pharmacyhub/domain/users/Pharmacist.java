package pharmacyhub.domain.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import pharmacyhub.domain.Location;
import pharmacyhub.domain.enums.UserType;

@Entity
@DiscriminatorValue("Pharmacist")
public class Pharmacist extends Employee{
	
	public Pharmacist() {
		super();
	}
	
	public Pharmacist(String email, String password, String name, String surname, String phoneNumber, Location location,
			UserType type) {
		super(email, password, name, surname, phoneNumber, location, type);
	}
	
}
