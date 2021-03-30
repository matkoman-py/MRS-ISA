package pharmacyhub.domain.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import pharmacyhub.domain.Location;
import pharmacyhub.domain.enums.UserType;

@Entity
@DiscriminatorValue("Dermatologist")
public class Dermatologist extends Employee{

	public Dermatologist() {
		super();
	}
	
	public Dermatologist(String email, String password, String name, String surname, String phoneNumber, Location location, boolean status, String activationCode) {
		super(email, password, name, surname, phoneNumber, location, UserType.Dermatologist, status, activationCode);
	}
	
	
}
