package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "drug_request")
@SQLDelete(sql = "UPDATE drug_request SET deleted = true WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class DrugRequest extends BaseEntity {
	
	@ManyToOne
	private Drugstore drugstore;
	
	@OneToOne
	private Drug drug;
	
	@Column(nullable = false)
	private boolean handled;
	
	public DrugRequest(Drugstore drugstore, Drug drug, boolean handled) {
		super();
		this.drugstore = drugstore;
		this.drug = drug;
		this.handled = handled;
	}

	public DrugRequest() {
		super();
	}

	public Drugstore getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	
}
