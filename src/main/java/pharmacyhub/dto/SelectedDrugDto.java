package pharmacyhub.dto;

public class SelectedDrugDto {
	
	String name;
	String available_in_this_drugstore;
	String amount_available;
	String requested;
	String amount;
	
	public SelectedDrugDto() {
		
	}
	
	public SelectedDrugDto(String name, String available_in_this_drugstore, String amount_available, String requested, String amount) {
		super();
		this.name = name;
		this.available_in_this_drugstore = available_in_this_drugstore;
		this.amount_available = amount_available;
		this.requested = requested;
		this.amount = amount;
	}

	public String getAvailable_in_this_drugstore() {
		return available_in_this_drugstore;
	}

	public void setAvailable_in_this_drugstore(String available_in_this_drugstore) {
		this.available_in_this_drugstore = available_in_this_drugstore;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount_available() {
		return amount_available;
	}

	public void setAmount_available(String amount_available) {
		this.amount_available = amount_available;
	}

	public String getRequested() {
		return requested;
	}

	public void setRequested(String requested) {
		this.requested = requested;
	}
	
}
