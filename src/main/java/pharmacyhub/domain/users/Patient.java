package pharmacyhub.domain.users;

import pharmacyhub.domain.Location;
import pharmacyhub.domain.PatientCategory;
import pharmacyhub.domain.enums.UserType;

public class Patient extends User {

	// TODO add allergens
	private int points;
	private int penaltyCounter;
	private PatientCategory category;

	public Patient() {
		super();
	}

	public Patient(String email, String password, String name, String surname, String phoneNumber, Location location,
			UserType type, int points, int penaltyCounter, PatientCategory category) {
		super(email, password, name, surname, phoneNumber, location, type);
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
