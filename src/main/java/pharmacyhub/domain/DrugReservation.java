package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import pharmacyhub.domain.enums.DrugReservationStatus;
import pharmacyhub.domain.users.Patient;

@Entity
@Table(name = "drug_reservation")
@SQLDelete(sql = "UPDATE drug_reservation SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class DrugReservation extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "drug_fk")
	private Drug drug;
	@ManyToOne
	@JoinColumn(name = "drugstore_fk")
	private Drugstore drugstore;
	@Column(nullable = false)
	private int amount;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id", nullable = true)
	private Patient patient; // bice patient objekat
	@Column(nullable = false)
	private String date;
	@Column
	private String confirmationCode;
	@Column
	private double price;
	@Column(nullable = false)
	private DrugReservationStatus status;
	@Column
	private boolean eReceipt;

	public DrugReservation() {
		super();
	}

	public DrugReservation(Drug drug, Drugstore drugstore, int amount, Patient patient, String date,boolean eReceipt) {
		super();
		this.drug = drug;
		this.drugstore = drugstore;
		this.amount = amount;
		this.patient = patient;
		this.date = date;
		this.status = DrugReservationStatus.Pending;
		this.eReceipt = eReceipt;
	}

	public DrugReservation(Drug drug, Drugstore drugstore, int amount, Patient patient, String date,
			String confirmationCode, double price) {
		super();
		this.drug = drug;
		this.drugstore = drugstore;
		this.amount = amount;
		this.patient = patient;
		this.date = date;
		this.confirmationCode = confirmationCode;
		this.price = price;
		this.status = DrugReservationStatus.Pending;
	}

	public boolean iseReceipt() {
		return eReceipt;
	}

	public void seteReceipt(boolean eReceipt) {
		this.eReceipt = eReceipt;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Drugstore getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public DrugReservationStatus getStatus() {
		return status;
	}

	public void setStatus(DrugReservationStatus status) {
		this.status = status;
	}

	

	
}
