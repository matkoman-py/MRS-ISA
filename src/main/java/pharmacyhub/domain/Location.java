package pharmacyhub.domain;

import javax.persistence.Entity;

@Entity
public class Location extends BaseEntity{
	
	private String address;
	private String city;
	private String country;
	
	public Location() {
	
	}

	public Location(String id, String address, String city, String country) {
		super();
		this.id = id;
		this.address = address;
		this.city = city;
		this.country = country;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
