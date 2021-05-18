package pharmacyhub.dto;

import java.sql.Date;
import java.sql.Time;

import pharmacyhub.domain.enums.OrderStatus;

public class DrugOrderOverviewDto {
	
	String drugs;
	OrderStatus status;
	Date expirationDate;
	Time expirationTime;
	String orderId;
	
	public DrugOrderOverviewDto() {
		
	}
	
	public DrugOrderOverviewDto(String drugs, OrderStatus status, Date expirationDate, Time expriationTime, String orderId) {
		super();
		this.drugs = drugs;
		this.status = status;
		this.expirationDate = expirationDate;
		this.expirationTime = expriationTime;
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDrugs() {
		return drugs;
	}
	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Time getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(Time expriationTime) {
		this.expirationTime = expriationTime;
	}

}
