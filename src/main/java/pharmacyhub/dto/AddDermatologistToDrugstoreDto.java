package pharmacyhub.dto;

public class AddDermatologistToDrugstoreDto {

	private String dermatologistEmail;
	private String drugstoreId;

	public String getDermatologistEmail() {
		return dermatologistEmail;
	}

	public void setDermatologistEmail(String dermatologistEmail) {
		this.dermatologistEmail = dermatologistEmail;
	}

	public String getDrugstoreId() {
		return drugstoreId;
	}

	public void setDrugstoreId(String drugstoreId) {
		this.drugstoreId = drugstoreId;
	}

}
