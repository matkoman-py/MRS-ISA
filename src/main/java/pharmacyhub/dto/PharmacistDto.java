package pharmacyhub.dto;

import pharmacyhub.domain.users.Pharmacist;

public class PharmacistDto {
	
	String id;
    String name;
    String surname;
    String email;
    double rating;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public PharmacistDto(String id, String name, String surname, String email, double rating) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.rating = rating;
	}
	public PharmacistDto() {
		super();
	}
	
}
