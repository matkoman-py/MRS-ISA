package pharmacyhub.dto.ereceipt;

import pharmacyhub.domain.Drugstore;

public class DrugstoreAndPriceDto {
	private Drugstore drugstore;
	private double totalPrice;

	public DrugstoreAndPriceDto() {
		super();
	}

	public DrugstoreAndPriceDto(Drugstore drugstore, double totalPrice) {
		super();
		this.drugstore = drugstore;
		this.totalPrice = totalPrice;
	}

	public Drugstore getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
