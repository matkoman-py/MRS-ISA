package pharmacyhub.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Employment;
import pharmacyhub.domain.Location;
import pharmacyhub.domain.Point;

public class DrugstoreDtoAll {
	public String id;
	public String name;

	
	public Location location;

	
	public String description;

	
	public double rating;

	
	private List<Employment> employements;

	
	private List<DrugStock> drugStock;

	
	private String workingHoursFrom;

	
	private String workingHoursTo;

	
	private int pharmacistAppointmentPrice;
	
	
	private Point point;


	public DrugstoreDtoAll(String name, Location location, String description, double rating,
			List<Employment> employements, List<DrugStock> drugStock, String workingHoursFrom, String workingHoursTo,
			int pharmacistAppointmentPrice, Point point) {
		super();
		this.name = name;
		this.location = location;
		this.description = description;
		this.rating = rating;
		this.employements = employements;
		this.drugStock = drugStock;
		this.workingHoursFrom = workingHoursFrom;
		this.workingHoursTo = workingHoursTo;
		this.pharmacistAppointmentPrice = pharmacistAppointmentPrice;
		this.point = point;
	}

	
	public DrugstoreDtoAll() {
		super();
	}
	

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


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public List<Employment> getEmployements() {
		return employements;
	}


	public void setEmployements(List<Employment> employements) {
		this.employements = employements;
	}


	public List<DrugStock> getDrugStock() {
		return drugStock;
	}


	public void setDrugStock(List<DrugStock> drugStock) {
		this.drugStock = drugStock;
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


	public int getPharmacistAppointmentPrice() {
		return pharmacistAppointmentPrice;
	}


	public void setPharmacistAppointmentPrice(int pharmacistAppointmentPrice) {
		this.pharmacistAppointmentPrice = pharmacistAppointmentPrice;
	}


	public Point getPoint() {
		return point;
	}


	public void setPoint(Point point) {
		this.point = point;
	}
	
	
}
