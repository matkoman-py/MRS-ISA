package pharmacyhub.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "UPDATE drug SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Drug extends BaseEntity {

	@Column(nullable = false, unique=true)
	private String name;
	
	@Column(nullable = false)
	private String form;
	
	@Column(nullable = false)
	private boolean receipt;
	
//	@Column(nullable = false)
//	private String counterEffects;
//	
//	@Column(nullable = false)
//	private int dailyDose;
//	
//	@Column(nullable = false)
//	private int weight;
	
	@ManyToOne
	@JoinColumn(name = "drug_type_fk", nullable = false)
	private DrugType type;
	
	@ManyToOne
	@JoinColumn(name = "manufacturer_fk", nullable = false)
	private Manufacturer manufacturer;
	
	@Transient
	//za sada, ovo ce se menjati
	private List<Drug> substitutions;
	
	@ManyToMany
	@JoinTable(name = "drug_ingredients", 
			   joinColumns = @JoinColumn(name = "drug_id"), 
			   inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private List<Ingredient> ingredients;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private int point;

	public Drug() {
		super();
	}

	public Drug(String id, String name, String form, boolean receipt, DrugType type, Manufacturer manufacturer,
			List<Drug> substitutions, List<Ingredient> ingredients, String description, int point) {
		super();
		this.name = name;
		this.form = form;
		this.receipt = receipt;
		this.type = type;
		this.manufacturer = manufacturer;
		this.substitutions = substitutions;
		this.ingredients = ingredients;
		this.description = description;
		this.point = point;
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

}
