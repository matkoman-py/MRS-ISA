package pharmacyhub.domain.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.Ingredient;
import pharmacyhub.domain.Location;
import pharmacyhub.domain.PatientCategory;
import pharmacyhub.domain.enums.UserType;

@Entity
@DiscriminatorValue("Patient")
public class Patient extends User {

	// TODO add allergens
	@ManyToMany
	@JoinTable(name = "patient_allergens", 
			   joinColumns = @JoinColumn(name = "patient_id"), 
			   inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private List<Ingredient> allergens;
	
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
			boolean status, String activationCode, int points, int penaltyCounter, PatientCategory category, List<Ingredient> allergens) {
		super(email, password, name, surname, phoneNumber, location, UserType.Patient, status, activationCode);
		this.points = points;
		this.penaltyCounter = penaltyCounter;
		this.category = category;
		this.allergens = allergens;
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

	public List<Ingredient> getAllergens() {
		return allergens;
	}

	public void setAllergens(List<Ingredient> allergens) {
		this.allergens = allergens;
	}

}
