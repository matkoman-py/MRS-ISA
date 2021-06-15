package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pharmacyhub.domain.users.Patient;

@Entity
@SQLDelete(sql = "UPDATE rating_drug SET deleted = true WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class RatingDrug extends BaseEntity{
	
	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name = "patient_fk")
	private Patient patient;
	
	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name = "drug_fk")
	private Drug drug;
	
	@Column(nullable = false)
	private Integer rating;

	public RatingDrug() {
		super();
	}

	public RatingDrug(Patient patient, Drug drug, Integer rating) {
		super();
		this.patient = patient;
		this.drug = drug;
		this.rating = rating;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
