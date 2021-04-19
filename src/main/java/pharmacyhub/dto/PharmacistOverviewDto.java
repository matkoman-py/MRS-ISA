package pharmacyhub.dto;

public class PharmacistOverviewDto {
	
	private String name;
	private String surname;
	private double averageRate;
	private String drugstoreName;
	
	public PharmacistOverviewDto() {
		
	}
	
	public PharmacistOverviewDto(String name, String surname, double averageRate, String drugstoreName) {
		super();
		this.name = name;
		this.surname = surname;
		this.averageRate = averageRate;
		this.drugstoreName = drugstoreName;
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
	public double getAverageRate() {
		return averageRate;
	}
	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}
	public String getDrugstoreName() {
		return drugstoreName;
	}
	public void setDrugstoreName(String drugstoreName) {
		this.drugstoreName = drugstoreName;
	}

}
