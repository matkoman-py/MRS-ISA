package pharmacyhub.dto;

import pharmacyhub.domain.users.Dermatologist;

public class HireDermatologistDto {
	
	private String dermatologistId;
	private String drugstoreId;
	private String workingHoursFrom;
	private String workingHoursTo;
	
	public HireDermatologistDto() {
		
	}
	
	public HireDermatologistDto(String dermatologistId, String drugstoreId, String workingHoursFrom, String workingHoursTo) {
		super();
		this.dermatologistId = dermatologistId;
		this.drugstoreId = drugstoreId;
		this.workingHoursFrom = workingHoursFrom;
		this.workingHoursTo = workingHoursTo;
	}

	public String getDrugstoreId() {
		return drugstoreId;
	}

	public void setDrugstoreId(String drugstoreId) {
		this.drugstoreId = drugstoreId;
	}

	public String getDermatologistId() {
		return dermatologistId;
	}

	public void setDermatologistId(String dermatologistId) {
		this.dermatologistId = dermatologistId;
	}

	public String getWorkingHoursFrom() {
		return workingHoursFrom;
	}

	public void setWorkingHoursFrom(String workingHoursFrom) {
		this.workingHoursFrom = workingHoursFrom;
	}

	public String getWorkingHoursTo() {
		return workingHoursTo;
	}

	public void setWorkingHoursTo(String workingHoursTo) {
		this.workingHoursTo = workingHoursTo;
	}
	
	
	
}
