package pharmacyhub.dto;

public class AddDermatologistToDrugstoreDto {
	
	private String dermatologistId;
	private String drugstoreId;

	public String getDermatologistId() {
		return dermatologistId;
	}

	public void setDermatologistId(String dermatologistId) {
		this.dermatologistId = dermatologistId;
	}

	public String getDrugstoreId() {
		return drugstoreId;
	}

	public void setDrugstoreId(String drugstoreId) {
		this.drugstoreId = drugstoreId;
	}

}
