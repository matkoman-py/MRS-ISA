package pharmacyhub.dto;

public class DrugReservationEmployeeDto {
	private String patientId;
	private String drugstoreId;
	private String drugId;
	private String date;
	private int amount;
	private int duration;
	private String appointmentId;
	private String check;
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public DrugReservationEmployeeDto(String patientId, String drugstoreId, String drugId, String date, int amount, int duration,
			String appointmentId, String check) {
		super();
		this.patientId = patientId;
		this.drugstoreId = drugstoreId;
		this.drugId = drugId;
		this.date = date;
		this.amount = amount;
		this.duration = duration;
		this.appointmentId = appointmentId;
		this.check = check;
	    
	}
	public DrugReservationEmployeeDto() {
		super();
	}
	
}
