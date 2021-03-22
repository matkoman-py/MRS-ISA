package pharmacyhub.domain;

import java.util.List;

public class Drug {

	private String id;
	private String name;
	private String form;
	private boolean receipt;
	private DrugType type; //dodaj neke predef
	private Manufacturer manufatcuter;
	private List<Drug> substitutions; //dodaj neke predef
	private List<Ingrediants> ingrediants; //dodaj neke predef
	private String description;
	private int point;

	public Drug() {
		super();
	}

	public Drug(String id, String name, String form, boolean receipt, DrugType type, Manufacturer manufatcuter,
			List<Drug> substitutions, List<Ingrediants> ingrediants, String description, int point) {
		super();
		this.id = id;
		this.name = name;
		this.form = form;
		this.receipt = receipt;
		this.type = type;
		this.manufatcuter = manufatcuter;
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

	public Manufacturer getManufatcuter() {
		return manufatcuter;
	}

	public void setManufatcuter(Manufacturer manufatcuter) {
		this.manufatcuter = manufatcuter;
	}

	public List<Drug> getSubstitutions() {
		return substitutions;
	}

	public void setSubstitutions(List<Drug> substitutions) {
		this.substitutions = substitutions;
	}

	public List<Ingrediants> getIngrediants() {
		return ingrediants;
	}

	public void setIngrediants(List<Ingrediants> ingrediants) {
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
