package pharmacyhub.domain.complaints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import pharmacyhub.domain.BaseEntity;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.ComplaintType;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.User;

@Entity
public class Complaint extends BaseEntity {

	private String text;

	@ManyToOne
	@JoinColumn(name = "patient_fk", nullable = false)
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "employee_fk", nullable = false)
	private User employee;

	@ManyToOne
	@JoinColumn(name = "drugstore_fk", nullable = false)
	private Drugstore drugstore;

	@Column(nullable = false)
	private ComplaintType type;

	public Complaint() {
		super();
	}

	public Complaint(String id, String text, Patient patient, User employee, Drugstore drugstore, ComplaintType type) {
		super();
		this.id = id;
		this.text = text;
		this.patient = patient;
		this.employee = employee;
		this.drugstore = drugstore;
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public Drugstore getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
	}

	public ComplaintType getType() {
		return type;
	}

	public void setType(ComplaintType type) {
		this.type = type;
	}

}
