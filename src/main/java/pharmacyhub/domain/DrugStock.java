package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "drugstock")
@SQLDelete(sql = "UPDATE drugstock SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class DrugStock extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "drug_fk")
	private Drug drug;
	@ManyToOne
	@JoinColumn(name = "drugstore_fk")
	private Drugstore drugstore;
	@Column(nullable = false)
	private int amount;
	
	public DrugStock() {
		
	}
	
	public DrugStock(Drug drug, Drugstore drugstore, int amount) {
		super();
		this.drug = drug;
		this.drugstore = drugstore;
		this.amount = amount;
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
	
}
