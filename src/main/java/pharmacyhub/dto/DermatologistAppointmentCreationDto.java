package pharmacyhub.dto;

import pharmacyhub.domain.users.Dermatologist;

public class DermatologistAppointmentCreationDto {
	
	private Dermatologist dermatologist;
	private String workingHoursFrom;
	private String workingHoursTo;
	
	public DermatologistAppointmentCreationDto() {
		
	}
	
	public DermatologistAppointmentCreationDto(Dermatologist dermatologist, String workingHoursFrom,
			String workingHoursTo) {
		super();
		this.dermatologist = dermatologist;
		this.workingHoursFrom = workingHoursFrom;
		this.workingHoursTo = workingHoursTo;
	}
	public Dermatologist getDermatologist() {
		return dermatologist;
	}
	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
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
