package pharmacyhub.dto.complaint;

import pharmacyhub.domain.enums.ComplaintType;

public class MakeComplaintDto {

	private String patientId;
	private String employeeId;
	private String drugstoreId;
	private ComplaintType type;
	private String text;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDrugstoreId() {
		return drugstoreId;
	}

	public void setDrugstoreId(String drugstoreId) {
		this.drugstoreId = drugstoreId;
	}

	public ComplaintType getType() {
		return type;
	}

	public void setType(ComplaintType type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
