package pharmacyhub.dto.supplier;

public class SupplierStockDto {

	private String supplierId;
	private String drugId;
	private String drugName;
	private String id;
	private int amount;



	public SupplierStockDto() {
		super();
	}

	public SupplierStockDto(String supplierId, String drugId, String drugName, String id, int amount) {
		super();
		this.supplierId = supplierId;
		this.drugId = drugId;
		this.drugName = drugName;
		this.id = id;
		this.amount = amount;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
