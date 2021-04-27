package pharmacyhub.dto;

public class SubscriptionDto {
	
	private String patientId;
	private String drugstoreId;
	
	public SubscriptionDto() {
		
	}
	
	public SubscriptionDto(String patientId, String drugstoreId) {
		super();
		this.patientId = patientId;
		this.drugstoreId = drugstoreId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getDrugstoreId() {
		return drugstoreId;
	}
	public void setDrugstoreId(String drugstoreId) {
		this.drugstoreId = drugstoreId;
	}
	
}
