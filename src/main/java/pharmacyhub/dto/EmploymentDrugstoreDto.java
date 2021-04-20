package pharmacyhub.dto;

public class EmploymentDrugstoreDto {
	private String drugstore;
	private String name;
	private String surname;
	private String workingHoursFrom;
	private String workingHoursTo;
	public EmploymentDrugstoreDto() {
		super();
	}
	public EmploymentDrugstoreDto(String drugstore, String name, String surname, String workingHoursFrom,
			String workingHoursTo) {
		super();
		this.drugstore = drugstore;
		this.name = name;
		this.surname = surname;
		this.workingHoursFrom = workingHoursFrom;
		this.workingHoursTo = workingHoursTo;
	}
	public String getDrugstore() {
		return drugstore;
	}
	public void setDrugstore(String drugstore) {
		this.drugstore = drugstore;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
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
