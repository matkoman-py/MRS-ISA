package pharmacyhub.dto;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Location;
import pharmacyhub.domain.enums.UserType;

public class EmployeeDto {

	private String email;
	private String name;
	private String surname;
	private String phoneNumber;
	public Location location;
	private UserType type;
	private Drugstore drugstore;

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

	public Drugstore getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
	}

}
