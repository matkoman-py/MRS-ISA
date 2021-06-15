package pharmacyhub.dto;

import javax.persistence.Column;

public class LoyaltyConfigurationDto {
	private int id;

	
	private int dermatologistPointsGained;

	
	private int pharmacistPointsGained;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public LoyaltyConfigurationDto(int id, int dermatologistPointsGained, int pharmacistPointsGained) {
		super();
		this.id = id;
		this.dermatologistPointsGained = dermatologistPointsGained;
		this.pharmacistPointsGained = pharmacistPointsGained;
	}


	public LoyaltyConfigurationDto() {
		super();
	}
	
	
}
