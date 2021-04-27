package pharmacyhub.dto.drugOrder;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import pharmacyhub.domain.OrderStock;
import pharmacyhub.domain.enums.OrderStatus;

public class DrugOrderDto {

	private List<OrderStock> stock;
	private Date deadlineDate;
	private Time deadlineTime;
	private OrderStatus status;
	private String drugstoreId;
	private String drugstoreName;

	public List<OrderStock> getStock() {
		return stock;
	}

	public void setStock(List<OrderStock> stock) {
		this.stock = stock;
	}

	public Date getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public Time getDeadlineTime() {
		return deadlineTime;
	}

	public void setDeadlineTime(Time deadlineTime) {
		this.deadlineTime = deadlineTime;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getDrugstoreId() {
		return drugstoreId;
	}

	public void setDrugstoreId(String drugstoreId) {
		this.drugstoreId = drugstoreId;
	}

	public String getDrugstoreName() {
		return drugstoreName;
	}

	public void setDrugstoreName(String drugstoreName) {
		this.drugstoreName = drugstoreName;
	}

}
