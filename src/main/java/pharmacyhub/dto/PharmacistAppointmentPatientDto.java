package pharmacyhub.dto;

import java.sql.Time;
import java.util.Date;

public class PharmacistAppointmentPatientDto {
	private String pharmacistId;
	private Date date;
	private String time;
	private int duration;
	private String patientId;
	public PharmacistAppointmentPatientDto() {
		super();
	}
	public PharmacistAppointmentPatientDto(String pharmacistId, Date date, String time, int duration, String patientId) {
		super();
		this.pharmacistId = pharmacistId;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.patientId = patientId;
	}
	public String getPharmacistId() {
		return pharmacistId;
	}
	public void setPharmacistId(String pharmacistId) {
		this.pharmacistId = pharmacistId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
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
	
}
