package pharmacyhub.domain.users;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import pharmacyhub.domain.Employement;
import pharmacyhub.domain.Location;
import pharmacyhub.domain.enums.UserType;

@Entity
@DiscriminatorValue("Dermatologist")
public class Dermatologist extends Employee {

	@OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employement> employements;

	public Dermatologist() {
		super();
	}

	public Dermatologist(String email, String password, String name, String surname, String phoneNumber,
			Location location, String workingHoursFrom, String workingHoursTo) {
		super(email, password, name, surname, phoneNumber, location, UserType.Dermatologist, true, "", workingHoursFrom,
				workingHoursTo);
	}

	public List<Employement> getEmployements() {
		return employements;
	}

	public void setEmployements(List<Employement> employements) {
		this.employements = employements;
	}

}
