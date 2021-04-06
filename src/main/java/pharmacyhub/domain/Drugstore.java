package pharmacyhub.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Drugstore extends BaseEntity {

	@Column(nullable = false)
	public String name;

	@ManyToOne
	@JoinColumn(name = "location_fk", nullable = false)
	public Location location;

	@Column(nullable = false)
	public String description;

	@Column(name = "average_rating")
	public double averageRating;

	@OneToMany(mappedBy = "drugstore", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employement> employements;
	
	@Column
	private String workingHoursFrom;

	@Column
	private String workingHoursTo;

	public Drugstore() {
		super();
	}

	public Drugstore(String name, Location location, String decription, double averageRating) {
		super();
		this.name = name;
		this.location = location;
		this.description = decription;
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
		return description;
	}

	public void setDecription(String decription) {
		this.description = decription;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employement> getEmployements() {
		return employements;
	}

	public void setEmployements(List<Employement> employements) {
		this.employements = employements;
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