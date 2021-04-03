package pharmacyhub.domain.users;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Location;
import pharmacyhub.domain.enums.UserType;

@Entity
@DiscriminatorValue("Dermatologist")
public class Dermatologist extends Employee{

	@ManyToMany
	@JoinTable(name = "dermatologist_drugstore", 
	   joinColumns = @JoinColumn(name = "dermatologist_id"), 
	   inverseJoinColumns = @JoinColumn(name = "drugstore_id"))
	private List<Drugstore> drugstore;
	
	public Dermatologist() {
		super();
	}
	

	public Dermatologist(String email, String password, String name, String surname, String phoneNumber, Location location,
			String workingHoursFrom, String workingHoursTo) {
		super(email, password, name, surname, phoneNumber, location, UserType.Dermatologist, true, "", workingHoursFrom, workingHoursTo);
	}
	
	
}
