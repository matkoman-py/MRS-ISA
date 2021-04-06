package pharmacyhub.dto;

import java.util.List;

import pharmacyhub.domain.Employment;
import pharmacyhub.domain.Location;
import pharmacyhub.domain.enums.UserType;
import pharmacyhub.domain.users.Dermatologist;

public class DermatologistDto {
	private String email;
	private String name;
	private String surname;
	private String phoneNumber;
	private Location location;
	private UserType type;
	private List<Employment> employements;


	public DermatologistDto(Dermatologist dermatologist) {
		super();
		this.email = dermatologist.getEmail();
		this.name = dermatologist.getName();
		this.surname = dermatologist.getSurname();
		this.phoneNumber = dermatologist.getPhoneNumber();
		this.location = dermatologist.getLocation();
		this.type = dermatologist.getType();
		this.employements = dermatologist.getEmployements();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public List<Employment> getDrugstores() {
		return employements;
	}

	public void setDrugstores(List<Employment> drugstores) {
		this.employements = drugstores;
	}

}
