package pharmacyhub.dto.search;

import java.util.HashMap;

public class EReceiptSearchDto {

	private String drugstoreName;
	//private Integer minimumRating;
	private Double fromPrice;
	private Double toPrice;
	private String drugstoreCity;
	private HashMap<String, Integer> receiptData;

	public HashMap<String, Integer> getReceiptData() {
		return receiptData;
	}

	public void setReceiptData(HashMap<String, Integer> receiptData) {
		this.receiptData = receiptData;
	}

	public String getDrugstoreName() {
		return drugstoreName;
	}

	public void setDrugstoreName(String drugstoreName) {
		this.drugstoreName = drugstoreName;
	}

	/*public Integer getMinimumRating() {
		return minimumRating;
	}

	public void setMinimumRating(Integer minimumRating) {
		this.minimumRating = minimumRating;
	}*/

	public Double getFromPrice() {
		return fromPrice;
	}

	public void setFromPrice(Double fromPrice) {
		this.fromPrice = fromPrice;
	}

	public Double getToPrice() {
		return toPrice;
	}

	public void setToPrice(Double toPrice) {
		this.toPrice = toPrice;
	}

	public String getDrugstoreCity() {
		return drugstoreCity;
	}

	public void setDrugstoreCity(String drugstoreCity) {
		this.drugstoreCity = drugstoreCity;
	}

}
