package pharmacyhub.dto;

import java.sql.Time;
import java.util.Date;

import pharmacyhub.domain.users.Dermatologist;

public class DermatologistAppointmentDto {
	
	private Dermatologist dermatologist;
	private String drugstoreId;
	private Date date;
	private Time time;
	private int duration;
	private int price;
	
	public DermatologistAppointmentDto() {
		
	}
	
	public DermatologistAppointmentDto(Dermatologist dermatologist, String drugstoreId, Date date, Time time, int duration, int price) {
		super();
		this.dermatologist = dermatologist;
		this.drugstoreId = drugstoreId;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.price = price;
	}
  
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}

	public String getDrugstoreId() {
		return drugstoreId;
	}

	public void setDrugstoreId(String drugstoreId) {
		this.drugstoreId = drugstoreId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
