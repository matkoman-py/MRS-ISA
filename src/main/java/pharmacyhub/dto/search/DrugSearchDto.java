package pharmacyhub.dto.search;

public class DrugSearchDto {
	
	private String name;
	
	private String form;
	
	private String type;
	
	private String manufacturerId;
	
	private Boolean receipt;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(String manufacturer) {
		this.manufacturerId = manufacturer;
	}

	public Boolean isReceipt() {
		return receipt;
	}

	public void setReceipt(Boolean receipt) {
		this.receipt = receipt;
	}

}
