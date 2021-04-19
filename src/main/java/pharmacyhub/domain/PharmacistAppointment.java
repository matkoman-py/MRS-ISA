package pharmacyhub.domain;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.Pharmacist;

@Entity
public class PharmacistAppointment extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "pharmacist_fk", nullable = false)
	private Pharmacist pharmacist;
	@Column(nullable = false)
	private Date date;
	@Column(nullable = false)
	private String time;
	@Column(nullable = false)
	private int duration;	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id",nullable = true)
	private Patient patient; //bice patient objekat
	@Column(nullable = true)
	private String appointmentReport; //bice appointmentReport objekat
	
	public PharmacistAppointment() {
		super();
	}
	
	public PharmacistAppointment(Pharmacist pharmacist, Date date, String time, int duration, Patient patient,
			String appointmentReport) {
		super();
		this.pharmacist = pharmacist;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.patient = patient;
		this.appointmentReport = appointmentReport;
	}
	
	public Pharmacist getPharmacist() {
		return pharmacist;
	}
	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
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
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getAppointmentReport() {
		return appointmentReport;
	}
	public void setAppointmentReport(String appointmentReport) {
		this.appointmentReport = appointmentReport;
	}
	
}
