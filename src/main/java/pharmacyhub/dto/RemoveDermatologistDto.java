package pharmacyhub.dto;

public class RemoveDermatologistDto {
	
	private String dermatologistEmail;
	private String drugstoreId;
	
	public RemoveDermatologistDto() {
		
	}
	
	public RemoveDermatologistDto(String dermatologistEmail, String drugstoreId) {
		super();
		this.dermatologistEmail = dermatologistEmail;
		this.drugstoreId = drugstoreId;
	}
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
