package pharmacyhub.domain.users;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;

import pharmacyhub.domain.Location;
import pharmacyhub.domain.enums.UserType;

@MappedSuperclass
public class Employee extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// TODO: add work schedule
	@Column
	private String workingHoursFrom;
	@Column
	private String workingHoursTo;
	
	@Column
	@ColumnDefault("0")
	private double rating;

	public Employee() {
		super();
	}

	public Employee(String email, String password, String name, String surname, String phoneNumber, Location location,
			UserType type, boolean status, String activationCode, String workingHoursFrom, String workingHoursTo) {
		super(email, password, name, surname, phoneNumber, location, type, status, activationCode);
		this.workingHoursFrom = workingHoursFrom;
		this.workingHoursTo = workingHoursTo;
		
	}
	
	

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getWorkingHoursFrom() {
		return workingHoursFrom;
	}

	public void setWorkingHoursFrom(String workingHoursFrom) {
		this.workingHoursFrom = workingHoursFrom;
	}

	public String getWorkingHoursTo() {
		return workingHoursTo;
	}

	public void setWorkingHoursTo(String workingHoursTo) {
		this.workingHoursTo = workingHoursTo;
	}
}
