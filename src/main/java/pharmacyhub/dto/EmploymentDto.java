package pharmacyhub.dto;

public class EmploymentDto {
	
	private String id;
	private String name;
	private String surname;
	private String workingHoursFrom;
	private String workingHoursTo;
	
	public EmploymentDto() {
		
	}
	
	public EmploymentDto(String id,String name, String surname, String workingHoursFrom, String workingHoursTo) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.workingHoursFrom = workingHoursFrom;
		this.workingHoursTo = workingHoursTo;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
