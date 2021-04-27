package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import pharmacyhub.domain.users.Supplier;

@Entity
@Table(name = "supplierstock")
@SQLDelete(sql = "UPDATE supplierstock SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class SupplierStock extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "drug_fk")
	private Drug drug;

	@ManyToOne
	@JoinColumn(name = "supplier_fk")
	private Supplier supplier;

	@Column(nullable = false)
	private int amount;

	public SupplierStock() {
		super();
	}

	public SupplierStock(Drug drug, Supplier supplier, int amount) {
		super();
		this.drug = drug;
		this.supplier = supplier;
		this.amount = amount;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
