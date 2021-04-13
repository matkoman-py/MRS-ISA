package pharmacyhub.dto;

import java.sql.Time;
import java.util.Date;

import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Patient;

public class DermatologistAppointmentPatientDto {
	private String dermatologistId;
	private String drugstoreId;
	private Date date;
	private Time time;
	private int duration;
	private String patientId;
	private int price;
	
	public DermatologistAppointmentPatientDto(String dermatologistId, String drugstoreId, Date date, Time time,
			int duration, String patientId, int price) {
		super();
		this.dermatologistId = dermatologistId;
		this.drugstoreId = drugstoreId;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.patientId = patientId;
		this.price = price;
	}
	public String getDermatologistId() {
		return dermatologistId;
	}
	public void setDermatologistId(String dermatologistId) {
		this.dermatologistId = dermatologistId;
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
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
