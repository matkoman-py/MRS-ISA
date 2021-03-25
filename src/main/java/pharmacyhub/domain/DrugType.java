package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DrugType extends BaseEntity {

	@Column(nullable = false, unique=true)
	private String name;

	@Column(nullable = false)
	private String description;

	public DrugType() {
		super();
	}

	public DrugType(String id, String name, String description) {
		super();
		this.id = id;
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
