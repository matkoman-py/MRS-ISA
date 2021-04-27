package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class OrderStock extends BaseEntity {

//	@ManyToOne
//	private DrugOrder order;
	
	@ManyToOne
	private Drug drug;
	
	@Column(nullable = false)
	private int amount;

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
