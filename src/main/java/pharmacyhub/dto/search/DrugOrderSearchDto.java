package pharmacyhub.dto.search;

import pharmacyhub.domain.enums.OrderStatus;

public class DrugOrderSearchDto {

	private String drugstoreId;
	private OrderStatus orderStatus;

	
	public String getDrugstoreId() {
		return drugstoreId;
	}

	public void setDrugstoreId(String drugstoreId) {
		this.drugstoreId = drugstoreId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
