package pharmacyhub.dto;

import java.util.Date;

public class DrugStockPriceDto {
	private String drugId;
	private String drugName;
	private int drugAmount;
	private int drugPrice;
	private Date priceExpirationDate;

	public DrugStockPriceDto() {

	}

	public DrugStockPriceDto(String drugId, String drugName, int drugAmount, int drugPrice, Date priceExpirationDate) {
		super();
		this.drugId = drugId;
		this.drugName = drugName;
		this.drugAmount = drugAmount;
		this.drugPrice = drugPrice;
		this.priceExpirationDate = priceExpirationDate;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
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
