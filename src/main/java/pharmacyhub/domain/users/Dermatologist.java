package pharmacyhub.domain.users;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pharmacyhub.domain.Employment;
import pharmacyhub.domain.Location;
import pharmacyhub.domain.enums.UserType;

@Entity
@DiscriminatorValue("Dermatologist")
public class Dermatologist extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employment> employements;

	public Dermatologist() {
		super();
	}

	public Dermatologist(String email, String password, String name, String surname, String phoneNumber,
			Location location, String workingHoursFrom, String workingHoursTo) {
		super(email, password, name, surname, phoneNumber, location, UserType.Dermatologist, true, "", workingHoursFrom,
				workingHoursTo);
	}

	public List<Employment> getEmployements() {
		return employements;
	}

	public void setEmployements(List<Employment> employements) {
		this.employements = employements;
	}

}
