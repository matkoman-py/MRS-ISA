package pharmacyhub.dto.complaint;

import pharmacyhub.domain.enums.ComplaintType;

public class ComplaintDto {

	private String id;
	private String text;

	private String patientId;
	private String patientEmail;
	private ComplaintType type;

	private String drugstoreId;
	private String drugstoreName;

	private String employeeId;
	private String employeDrugstoreId;
	private String employeDrugstoreName;
	private String employeName;
	private String employeSurname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public ComplaintType getType() {
		return type;
	}

	public void setType(ComplaintType type) {
		this.type = type;
	}

	public String getDrugstoreId() {
		return drugstoreId;
	}

	public void setDrugstoreId(String drugstoreId) {
		this.drugstoreId = drugstoreId;
	}

	public String getDrugstoreName() {
		return drugstoreName;
	}

	public void setDrugstoreName(String drugstoreName) {
		this.drugstoreName = drugstoreName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeDrugstoreId() {
		return employeDrugstoreId;
	}

	public void setEmployeDrugstoreId(String employeDrugstoreId) {
		this.employeDrugstoreId = employeDrugstoreId;
	}

	public String getEmployeDrugstoreName() {
		return employeDrugstoreName;
	}

	public void setEmployeDrugstoreName(String employeDrugstoreName) {
		this.employeDrugstoreName = employeDrugstoreName;
	}

	public String getEmployeName() {
		return employeName;
	}

	public void setEmployeName(String employeName) {
		this.employeName = employeName;
	}

	public String getEmployeSurname() {
		return employeSurname;
	}

	public void setEmployeSurname(String employeSurname) {
		this.employeSurname = employeSurname;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
