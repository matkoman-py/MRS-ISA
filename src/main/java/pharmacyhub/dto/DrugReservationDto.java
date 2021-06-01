package pharmacyhub.dto;

public class DrugReservationDto {
	
	private String patientId;
	private String drugstoreId;
	private String drugId;
	private String date;
	private int amount;
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public DrugReservationDto(String patientId, String drugstoreId, String drugId, String date, int amount) {
		super();
		this.patientId = patientId;
		this.drugstoreId = drugstoreId;
		this.drugId = drugId;
		this.date = date;
		this.amount = amount;
	}
	public DrugReservationDto() {
		super();
	}
	
	
}
