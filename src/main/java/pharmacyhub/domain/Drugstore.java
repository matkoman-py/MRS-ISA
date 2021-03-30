package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Drugstore extends BaseEntity {

	@Column(nullable = false)
	public String name;
	
	@ManyToOne
	@JoinColumn(name="location_fk", nullable = false)
	public Location location;
	
	@Column(nullable = false)
	public String decription;
	
	@Column(name = "average_rating")
	public double averageRating;

	public Drugstore() {
		super();
	}

	public Drugstore(String name, Location location, String decription, double averageRating) {
		super();
		this.name = name;
		this.location = location;
		this.decription = decription;
		this.averageRating = averageRating;
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

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

}