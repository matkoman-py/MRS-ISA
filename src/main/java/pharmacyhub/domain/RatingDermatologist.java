package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Patient;

@Entity
@SQLDelete(sql = "UPDATE rating_dermatologist SET deleted = true WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class RatingDermatologist extends BaseEntity{
	
	public RatingDermatologist() {
		super();
	}

	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name = "patient_fk")
	private Patient patient;
	
	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name = "dermatologist_fk")
	private Dermatologist dermatologist;
	
	public RatingDermatologist(Patient patient, Dermatologist dermatologist, Integer rating) {
		super();
		this.patient = patient;
		this.dermatologist = dermatologist;
		this.rating = rating;
	}

	@Column(nullable = false)
	private Integer rating;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
