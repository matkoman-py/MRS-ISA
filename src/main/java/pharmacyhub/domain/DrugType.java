package pharmacyhub.domain;

public class DrugType {

	private String name;
	private String description;

	public DrugType() {
		super();
	}

	public DrugType(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
