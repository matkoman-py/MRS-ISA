package pharmacyhub.dto;

import java.util.Date;

public class DrugStockPriceDto {
	private String drugName;
	private int drugAmount;
	private int drugPrice;
	private Date priceExpirationDate;
	
	public DrugStockPriceDto() {
		
	}
	
	public DrugStockPriceDto(String drugName, int drugAmount, int drugPrice, Date priceExpirationDate) {
		super();
		this.drugName = drugName;
		this.drugAmount = drugAmount;
		this.drugPrice = drugPrice;
		this.priceExpirationDate = priceExpirationDate;
	}
	
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public int getDrugAmount() {
		return drugAmount;
	}
	public void setDrugAmount(int drugAmount) {
		this.drugAmount = drugAmount;
	}
	public int getDrugPrice() {
		return drugPrice;
	}
	public void setDrugPrice(int drugPrice) {
		this.drugPrice = drugPrice;
	}
	public Date getPriceExpirationDate() {
		return priceExpirationDate;
	}
	public void setPriceExpirationDate(Date priceExpirationDate) {
		this.priceExpirationDate = priceExpirationDate;
	}
	
}
