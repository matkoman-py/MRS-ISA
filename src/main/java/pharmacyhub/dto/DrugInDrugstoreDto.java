package pharmacyhub.dto;

import pharmacyhub.domain.DrugType;
import pharmacyhub.domain.Manufacturer;

public class DrugInDrugstoreDto {
	
	private String name;
	
	private String form;
	
	private boolean receipt;
	
	private DrugType type;
	
	private Manufacturer manufacturer;
	
	private int amount;
	
	public DrugInDrugstoreDto() {
		
	}

	public DrugInDrugstoreDto(String name, String form, boolean receipt, DrugType type, Manufacturer manufacturer,
			int amount) {
		super();
		this.name = name;
		this.form = form;
		this.receipt = receipt;
		this.type = type;
		this.manufacturer = manufacturer;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public boolean isReceipt() {
		return receipt;
	}

	public void setReceipt(boolean receipt) {
		this.receipt = receipt;
	}

	public DrugType getType() {
		return type;
	}

	public void setType(DrugType type) {
		this.type = type;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
