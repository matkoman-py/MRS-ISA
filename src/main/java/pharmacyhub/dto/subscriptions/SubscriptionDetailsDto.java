package pharmacyhub.dto.subscriptions;

import pharmacyhub.domain.Location;

public class SubscriptionDetailsDto {

	private String id;
	private String patientId;
	private String drugstoreId;
	private String name;
	private Location location;
	private String description;
	private double averageRating;
	private String workingHoursFrom;
	private String workingHoursTo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDrugstoreId() {
		return drugstoreId;
	}

	public void setDrugstoreId(String drugstoreId) {
		this.drugstoreId = drugstoreId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
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
