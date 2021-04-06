package pharmacyhub.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import pharmacyhub.domain.users.Dermatologist;

@Entity
public class DermatologistEmployment extends BaseEntity {
	//treba obrisati klasu?
	
	@ManyToOne
	@JoinColumn(name = "dermatologist_fk", nullable = false)
	private Dermatologist dermatologist;
	@ManyToOne
	@JoinColumn(name = "drugstore_fk", nullable = false)
	private Drugstore drugstore;
	
	public DermatologistEmployment() {
		
	}
	
	public DermatologistEmployment(Dermatologist dermatologist, Drugstore drugstore) {
		super();
		this.dermatologist = dermatologist;
		this.drugstore = drugstore;
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
	
}
