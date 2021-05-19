package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LoyaltyConfiguration {

	@Id
	private int id;

	@Column(nullable = false)
	private int dermatologistPointsGained;

	@Column(nullable = false)
	private int pharmacistPointsGained;

	public LoyaltyConfiguration() {
		super();
	}

	public LoyaltyConfiguration(int dermatologistPointsGained, int pharmacistPointsGained) {
		super();
		this.dermatologistPointsGained = dermatologistPointsGained;
		this.pharmacistPointsGained = pharmacistPointsGained;
	}

	public int getDermatologistPointsGained() {
		return dermatologistPointsGained;
	}

	public void setDermatologistPointsGained(int dermatologistPointsGained) {
		this.dermatologistPointsGained = dermatologistPointsGained;
	}

	public int getPharmacistPointsGained() {
		return pharmacistPointsGained;
	}

	public void setPharmacistPointsGained(int pharmacistPointsGained) {
		this.pharmacistPointsGained = pharmacistPointsGained;
	}

}
