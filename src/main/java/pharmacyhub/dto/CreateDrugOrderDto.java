package pharmacyhub.dto;

public class CreateDrugOrderDto {
	
	String drugName;
	boolean available;
	int availableAmount;
	boolean requested;
	
	public CreateDrugOrderDto() {
		
	}
	
	public CreateDrugOrderDto(String drugName, boolean available, int availableAmount, boolean requested) {
		super();
		this.drugName = drugName;
		this.available = available;
		this.availableAmount = availableAmount;
		this.requested = requested;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(int availableAmount) {
		this.availableAmount = availableAmount;
	}

	public boolean isRequested() {
		return requested;
	}

	public void setRequested(boolean requested) {
		this.requested = requested;
	}
	
}
