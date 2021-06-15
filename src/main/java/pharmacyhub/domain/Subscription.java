package pharmacyhub.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import pharmacyhub.domain.users.Patient;

@Entity
@SQLDelete(sql = "UPDATE subscription SET deleted = true WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class Subscription extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "patient_fk")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name = "drugstore_fk")
	private Drugstore drugstore;
	
	public Subscription() {
		
	}
	
	public Subscription(Patient patient, Drugstore drugstore) {
		super();
		this.patient = patient;
		this.drugstore = drugstore;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Drugstore getDrugstore() {
		return drugstore;
	}
	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
	}
	
}
