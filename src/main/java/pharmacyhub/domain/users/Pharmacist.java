package pharmacyhub.domain.users;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Location;
import pharmacyhub.domain.enums.UserType;

@Entity
@DiscriminatorValue("Pharmacist")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ? and version = ?")
public class Pharmacist extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name = "drugstore_fk")
	private Drugstore drugstore;
		
	public Pharmacist() {
		super();
	}

	public Pharmacist(String email, String password, String name, String surname, String phoneNumber, Location location, String workingHoursFrom, String workingHoursTo, Drugstore drugstore) {
		super(email, password, name, surname, phoneNumber, location, UserType.Pharmacist, true, null, workingHoursFrom, workingHoursTo);
		this.drugstore = drugstore;
	}
	
	public Drugstore getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
	}
}
