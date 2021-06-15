package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import pharmacyhub.domain.users.Patient;

@Entity
@SQLDelete(sql = "UPDATE rating_drugstore SET deleted = true WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class RatingDrugstore extends BaseEntity{

	public RatingDrugstore() {
		super();
	}

	public RatingDrugstore(Drugstore drugstore, Patient patient, Integer rating) {
		super();
		this.drugstore = drugstore;
		this.patient = patient;
		this.rating = rating;
	}

	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name = "drugstore_fk")
	private Drugstore drugstore;
	
	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name = "patient_fk")
	private Patient patient;
	
	@Column(nullable = false)
	private Integer rating;

	public Drugstore getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
}
