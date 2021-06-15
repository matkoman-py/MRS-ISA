package pharmacyhub.dto.ereceipt;

import java.util.HashMap;

public class SingleDrugstoreEReceiptDto {

	private String drugstoreId;
	private HashMap<String, Integer> receiptData;
	
	
	public String getDrugstoreId() {
		return drugstoreId;
	}
	public void setDrugstoreId(String drugstoreId) {
		this.drugstoreId = drugstoreId;
	}
	public HashMap<String, Integer> getReceiptData() {
		return receiptData;
	}
	public void setReceiptData(HashMap<String, Integer> receiptData) {
		this.receiptData = receiptData;
	}

}
