package pharmacyhub.dto.offer;

import java.sql.Date;
import java.sql.Time;

import pharmacyhub.domain.enums.OfferStatus;

public class OfferDto {

	private String drugOrderId;
	private int price;
	private OfferStatus status;
	private Date deliveryDate;
	private Time deliveryTime;
	private String supplierId;

	public String getDrugOrderId() {
		return drugOrderId;
	}

	public void setDrugOrderId(String drugOrderId) {
		this.drugOrderId = drugOrderId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public OfferStatus getStatus() {
		return status;
	}

	public void setStatus(OfferStatus status) {
		this.status = status;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Time getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Time deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

}
