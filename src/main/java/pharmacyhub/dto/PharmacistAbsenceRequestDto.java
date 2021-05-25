package pharmacyhub.dto;

import java.util.Date;

public class PharmacistAbsenceRequestDto {
	
	private String pharmacistName;
	private String pharmacistSurname;
	private Date startDate;
	private Date endDate;
	private String reason;
	private String requestId;
	
	public PharmacistAbsenceRequestDto() {
		
	}
	
	public PharmacistAbsenceRequestDto(String pharmacistName, String pharmacistSurname, Date startDate, Date endDate,
			String reason, String requestId) {
		super();
		this.pharmacistName = pharmacistName;
		this.pharmacistSurname = pharmacistSurname;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.requestId = requestId;
	}
	
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getPharmacistName() {
		return pharmacistName;
	}
	public void setPharmacistName(String pharmacistName) {
		this.pharmacistName = pharmacistName;
	}
	public String getPharmacistSurname() {
		return pharmacistSurname;
	}
	public void setPharmacistSurname(String pharmacistSurname) {
		this.pharmacistSurname = pharmacistSurname;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	

}
