package pharmacyhub.domain;

import java.util.List;

public class Drug {

	private String id;
	private String name;
	private String form;
	private boolean receipt;
	private DrugType type;
	private Manufacturer manufacturer;
	private List<Drug> substitutions;
	private List<Ingrediant> ingrediants;
	private String description;
	private int point;

	public Drug() {
		super();
	}

	public Drug(String id, String name, String form, boolean receipt, DrugType type, Manufacturer manufacturer,
			List<Drug> substitutions, List<Ingrediant> ingrediants, String description, int point) {
		super();
		this.id = id;
		this.name = name;
		this.form = form;
		this.receipt = receipt;
		this.type = type;
		this.manufacturer = manufacturer;
		this.substitutions = substitutions;
		this.ingrediants = ingrediants;
		this.description = description;
		this.point = point;
	}

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

	public List<Drug> getSubstitutions() {
		return substitutions;
	}

	public void setSubstitutions(List<Drug> substitutions) {
		this.substitutions = substitutions;
	}

	public List<Ingrediant> getIngrediants() {
		return ingrediants;
	}

	public void setIngrediants(List<Ingrediant> ingrediants) {
		this.ingrediants = ingrediants;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
