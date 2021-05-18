package pharmacyhub.dto;

public class AbsenceRequestDto {
	private String startDate;
	private String endDate;
	private String reason;
	private String employeeId;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public AbsenceRequestDto(String startDate, String endDate, String reason, String employeeId) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.employeeId = employeeId;
	}
	public AbsenceRequestDto() {
		super();
	}
	
}
