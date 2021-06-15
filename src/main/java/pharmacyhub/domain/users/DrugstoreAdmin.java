package pharmacyhub.domain.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;

import pharmacyhub.domain.Drugstore;

@Entity
@DiscriminatorValue("DrugstoreAdmin")
@SQLDelete(sql = "UPDATE drug_price SET deleted = true WHERE id = ? and version = ?")
public class DrugstoreAdmin extends User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "drugstore_fk")
	private Drugstore drugstore;

	public Drugstore getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
	}
	
	
}
