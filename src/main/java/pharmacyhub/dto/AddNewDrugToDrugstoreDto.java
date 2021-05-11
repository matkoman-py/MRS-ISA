package pharmacyhub.dto;

import java.util.Date;

import pharmacyhub.domain.Drug;

public class AddNewDrugToDrugstoreDto {
	
	String drugStoreId;
	Drug selectedDrug;
	int price;
	Date StartDate;
	Date endDate;
	
	public AddNewDrugToDrugstoreDto() {
		
	}
	
	public AddNewDrugToDrugstoreDto(String drugStoreId, Drug selectedDrug, int price, Date startDate, Date endDate) {
		super();
		this.drugStoreId = drugStoreId;
		this.selectedDrug = selectedDrug;
		this.price = price;
		StartDate = startDate;
		this.endDate = endDate;
	}

	public String getDrugStoreId() {
		return drugStoreId;
	}

	public void setDrugStoreId(String drugStoreId) {
		this.drugStoreId = drugStoreId;
	}

	public Drug getSelectedDrug() {
		return selectedDrug;
	}

	public void setSelectedDrug(Drug selectedDrug) {
		this.selectedDrug = selectedDrug;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
