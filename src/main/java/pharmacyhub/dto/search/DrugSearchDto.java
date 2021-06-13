package pharmacyhub.dto.search;

public class DrugSearchDto {

	private String id;

	private String name;

	private String form;

	private String type;

	private String manufacturerId;

	private Boolean receipt;

	private String sortByField;
	
	private Boolean ascending;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getSortByField() {
		return sortByField;
	}

	public void setSortByField(String sortByField) {
		this.sortByField = sortByField;
	}

	public Boolean getAscending() {
		return ascending;
	}

	public void setAscending(Boolean ascending) {
		this.ascending = ascending;
	}

	public Boolean getReceipt() {
		return receipt;
	}

	
}
