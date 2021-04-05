package pharmacyhub.dto;

import java.util.Date;

public class CreateNewPriceForDrugDto {
	
	private int price;
	private String drugName;
	private String drugStoreId;
	private Date startDate;
	private Date endDate;
	
	public CreateNewPriceForDrugDto() {
		
	}
	
	public CreateNewPriceForDrugDto(int price, String drugName, String drugStoreId, Date startDate, Date endDate) {
		super();
		this.price = price;
		this.drugName = drugName;
		this.drugStoreId = drugStoreId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDrugStoreId() {
		return drugStoreId;
	}
	public void setDrugStoreId(String drugStoreId) {
		this.drugStoreId = drugStoreId;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
}
