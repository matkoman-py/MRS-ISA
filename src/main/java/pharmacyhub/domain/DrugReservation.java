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

import pharmacyhub.domain.users.Patient;

@Entity
@Table(name = "drug_reservation")
@SQLDelete(sql = "UPDATE drug_reservation SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class DrugReservation extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "drug_fk")
	private Drug drug;
	@ManyToOne
	@JoinColumn(name = "drugstore_fk")
	private Drugstore drugstore;
	@Column(nullable = false)
	private int amount;
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id",nullable = true)
	private Patient patient; //bice patient objekat
	@Column(nullable = false)
	private String date;
	
	public DrugReservation() {
		super();
	}
	public DrugReservation(Drug drug, Drugstore drugstore, int amount, Patient patient, String date) {
		super();
		this.drug = drug;
		this.drugstore = drugstore;
		this.amount = amount;
		this.patient = patient;
		this.date = date;
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
	
	
}
