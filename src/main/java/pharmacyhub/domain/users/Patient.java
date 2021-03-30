package pharmacyhub.domain.users;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import pharmacyhub.domain.Location;
import pharmacyhub.domain.PatientCategory;
import pharmacyhub.domain.enums.UserType;

@Entity
@DiscriminatorValue("Patient")
public class Patient extends User {

	// TODO add allergens
	@Column
	private int points;
	
	@Column
	private int penaltyCounter;
	
	@ManyToOne
	@JoinTable(name = "patient_patientCategory", 
			   joinColumns = @JoinColumn(name = "patient_id"), 
			   inverseJoinColumns = @JoinColumn(name = "category_id"))
	private PatientCategory category;

	public Patient() {
		super();
	}

	public Patient(String email, String password, String name, String surname, String phoneNumber, Location location,
			boolean status, String activationCode, int points, int penaltyCounter, PatientCategory category) {
		super(email, password, name, surname, phoneNumber, location, UserType.Patient, status, activationCode, null, null);
		this.points = points;
		this.penaltyCounter = penaltyCounter;
		this.category = category;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPenaltyCounter() {
		return penaltyCounter;
	}

	public void setPenaltyCounter(int penaltyCounter) {
		this.penaltyCounter = penaltyCounter;
	}

	public PatientCategory getCategory() {
		return category;
	}

	public void setCategory(PatientCategory category) {
		this.category = category;
	}

}
