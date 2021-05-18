package pharmacyhub.dto.search;

public class DrugReservationCancelDto {
	
	private String drugReservationId;
	private String patientId;
	
	public String getDrugReservationId() {
		return drugReservationId;
	}
	public void setDrugReservationId(String drugReservationId) {
		this.drugReservationId = drugReservationId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	
}
