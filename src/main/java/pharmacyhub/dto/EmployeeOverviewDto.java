package pharmacyhub.dto;

import pharmacyhub.domain.Location;

public class EmployeeOverviewDto {
	
	private String name;
	private String surname;
	private double averageRate;
	private String email;
	private String phoneNumber;
	private Location location;
	private String type;
	
	public EmployeeOverviewDto() {
		
	}
	
	public EmployeeOverviewDto(String name, String surname, double averageRate, String email, String phoneNumber,
			Location location, String type) {
		super();
		this.name = name;
		this.surname = surname;
		this.averageRate = averageRate;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.location = location;
		this.type = type;
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

	public double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
