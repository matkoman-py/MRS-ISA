package pharmacyhub.dto;

public class DeleteDrugFromStockDto {
	
	String drugName;
	String drugStoreId;
	
	public DeleteDrugFromStockDto(String drugName, String drugStoreId) {
		super();
		this.drugName = drugName;
		this.drugStoreId = drugStoreId;
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
	
}
