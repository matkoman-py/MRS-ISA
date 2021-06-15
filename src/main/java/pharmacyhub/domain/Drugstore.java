package pharmacyhub.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SQLDelete(sql = "UPDATE drugstore SET deleted = true WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class Drugstore extends BaseEntity {

	@Column(nullable = false)
	public String name;

	@ManyToOne
	@JoinColumn(name = "location_fk", nullable = false)
	public Location location;

	@Column(nullable = false)
	public String description;

	@Column(name = "rating")
	@ColumnDefault("0")
	public double rating;

	@OneToMany(mappedBy = "drugstore", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employment> employements;

	@OneToMany(mappedBy = "drugstore", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<DrugStock> drugStock;

	@Column
	private String workingHoursFrom;

	@Column
	private String workingHoursTo;

	@Column
	private int pharmacistAppointmentPrice;
	
	@ManyToOne	
	@JoinColumn(name = "point_fk")
	private Point point;

	public Drugstore() {
		super();
	}

	public Drugstore(String name, Location location, String decription, double averageRating,
			int pharmacistAppointmentPrice, Point point) {
		super();
		this.name = name;
		this.location = location;
		this.description = decription;
		//this.averageRating = averageRating;
		this.pharmacistAppointmentPrice = pharmacistAppointmentPrice;
		this.point = point;
	}
	
	
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public List<DrugStock> getDrugStock() {
		return drugStock;
	}

	public void setDrugStock(List<DrugStock> drugStock) {
		this.drugStock = drugStock;
	}

	public int getPharmacistAppointmentPrice() {
		return pharmacistAppointmentPrice;
	}

	public void setPharmacistAppointmentPrice(int pharmacistAppointmentPrice) {
		this.pharmacistAppointmentPrice = pharmacistAppointmentPrice;
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

	//public double getAverageRating() {
	//	return averageRating;
	//}

	//public void setAverageRating(double averageRating) {
	//	this.averageRating = averageRating;
	//}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employment> getEmployements() {
		return employements;
	}

	public void setEmployements(List<Employment> employements) {
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