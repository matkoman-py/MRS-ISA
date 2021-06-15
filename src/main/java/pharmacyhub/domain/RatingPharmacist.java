package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.Pharmacist;

@Entity
@SQLDelete(sql = "UPDATE rating_pharmacist SET deleted = true WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class RatingPharmacist extends BaseEntity {
	
	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name = "patient_fk")
	private Patient patient;
	
	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name = "pharmacist_fk")
	private Pharmacist pharmacist;
	
	@Column(nullable = false)
	private Integer rating;

	public RatingPharmacist() {
		super();
	}

	public RatingPharmacist(Patient patient, Pharmacist pharmacist, Integer rating) {
		super();
		this.patient = patient;
		this.pharmacist = pharmacist;
		this.rating = rating;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
