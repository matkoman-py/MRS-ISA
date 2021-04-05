package pharmacyhub.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DrugPrice extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "drug_fk")
	private Drug drug;
	@ManyToOne
	@JoinColumn(name = "drugstore_fk")
	private Drugstore drugstore;
	@Column(nullable = false)
	private int price;
	@Column(nullable = false)
	private Date startDate;
	@Column(nullable = false)
	private Date endDate;
	
	public DrugPrice() {
		
	}
	
	public DrugPrice(Drug drug, Drugstore drugstore, int price, Date startDate, Date endDate) {
		super();
		this.drug = drug;
		this.drugstore = drugstore;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
