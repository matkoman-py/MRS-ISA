package pharmacyhub.dto;

import java.util.List;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugType;
import pharmacyhub.domain.Ingredient;
import pharmacyhub.domain.Manufacturer;

public class DrugDto {
	private String id;
	private String name;

	
	private String form;

	
	private boolean receipt;


	private String code;

	
	private int dailyDose;

	
	private double weight;

	
	private DrugType type;

	
	private Manufacturer manufacturer;

	
	private List<Drug> substitutions;

	
	private List<Ingredient> ingredients;

	private String description;

	
	private int point;
	
	private double rating;

	public DrugDto(String name, String form, boolean receipt, String code, int dailyDose, double weight, DrugType type,
			Manufacturer manufacturer, List<Drug> substitutions, List<Ingredient> ingredients, String description,
			int point, double rating) {
		super();
		this.name = name;
		this.form = form;
		this.receipt = receipt;
		this.code = code;
		this.dailyDose = dailyDose;
		this.weight = weight;
		this.type = type;
		this.manufacturer = manufacturer;
		this.substitutions = substitutions;
		this.ingredients = ingredients;
		this.description = description;
		this.point = point;
		this.rating = rating;
	}
	
	public DrugDto() {
		super();
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getDailyDose() {
		return dailyDose;
	}

	public void setDailyDose(int dailyDose) {
		this.dailyDose = dailyDose;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
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

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
}
