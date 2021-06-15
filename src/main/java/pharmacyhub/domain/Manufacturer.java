package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "UPDATE manufacturer SET deleted = true WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class Manufacturer extends BaseEntity {

	@Column(nullable = false, unique=true)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="location_fk", nullable = false)
	private Location location;
	
	@Column(nullable = false)
	private String phoneNumber;

	public Manufacturer() {
		super();
	}
	
	public Manufacturer(String name, Location location, String phoneNumber) {
		super();
		this.name = name;
		this.location = location;
		this.phoneNumber = phoneNumber;
	}

	public Manufacturer(String id, String name, Location location, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
