package pharmacyhub.dto;

import javax.persistence.Column;

public class PatientCategoryDto {
	private String id;
	private String name;
	
	
	private int requieredPoints;
	
	
	private int discount;

	
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


	public int getRequieredPoints() {
		return requieredPoints;
	}


	public void setRequieredPoints(int requieredPoints) {
		this.requieredPoints = requieredPoints;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public PatientCategoryDto(String name, int requieredPoints, int discount) {
		super();
		this.name = name;
		this.requieredPoints = requieredPoints;
		this.discount = discount;
	}


	public PatientCategoryDto() {
		super();
	}
	
	
}
