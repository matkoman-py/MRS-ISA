package pharmacyhub.domain.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Location;
import pharmacyhub.domain.enums.UserType;

@Entity
@DiscriminatorValue("Pharmacist")
public class Pharmacist extends Employee {

	@ManyToOne
	@JoinColumn(name = "drugstore_fk")
	private Drugstore drugstore;
		
	public Pharmacist() {
		super();
	}

	public Pharmacist(String email, String password, String name, String surname, String phoneNumber, Location location, String workingHoursFrom, String workingHoursTo) {
		super(email, password, name, surname, phoneNumber, location, UserType.Pharmacist, true, null, workingHoursFrom, workingHoursTo);
	}
	
	public Drugstore getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
	}
}
