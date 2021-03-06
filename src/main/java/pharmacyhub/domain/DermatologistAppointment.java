package pharmacyhub.domain;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Patient;

@Entity
@SQLDelete(sql = "UPDATE dermatologist_appointment SET deleted = true WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class DermatologistAppointment extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "dermatologist_fk", nullable = false)
	private Dermatologist dermatologist;
	@ManyToOne
	@JoinColumn(name = "drugstore_fk", nullable = false)
	private Drugstore drugstore;
	@Column(nullable = false)
	private Date date;
	@Column(nullable = false)
	private Time time;
	@Column(nullable = false)
	private int duration;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id", nullable = true)
	private Patient patient; // bice patient objekat
	@Column(nullable = true)
	private String appointmentReport; // bice appointmentReport objekat
	@Column(nullable = false)
	private double price;
	@Column(nullable = true)
	private Time timeEnd;
	@Column(nullable = true)
	private boolean processed;

	public DermatologistAppointment() {

	}

	public DermatologistAppointment(Dermatologist dermatologist, Drugstore drugstore, Date date, Time time,
			int duration, Patient patient, String appointmentReport, double price, boolean processed) {
		super();
		this.dermatologist = dermatologist;
		this.drugstore = drugstore;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.patient = patient;
		this.appointmentReport = appointmentReport;
		this.price = price;
		this.timeEnd = new Time(time.getTime() + (60000 * this.duration));
		this.processed = processed;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}

	public Drugstore getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient2) {
		this.patient = patient2;
	}

	public String getAppointmentReport() {
		return appointmentReport;
	}

	public void setAppointmentReport(String appointmentReport) {
		this.appointmentReport = appointmentReport;
	}

	public Time getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Time timeEnd) {
		this.timeEnd = timeEnd;
	}

}
