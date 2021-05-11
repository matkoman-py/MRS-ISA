package pharmacyhub.dto.search;

public class DrugOrderSearchDto {

	private String drugstoreId;
	private String orderStatus;
	
	public DrugOrderSearchDto() {
		
	}

	public DrugOrderSearchDto(String drugstoreId, String orderStatus) {
		super();
		this.drugstoreId = drugstoreId;
		this.orderStatus = orderStatus;
	}

	public String getDrugstoreId() {
		return drugstoreId;
	}

	public void setDrugstoreId(String drugstoreId) {
		this.drugstoreId = drugstoreId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
