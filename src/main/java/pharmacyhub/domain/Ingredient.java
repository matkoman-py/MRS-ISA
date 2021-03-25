package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Ingredient extends BaseEntity {

	@Column(nullable = false, unique=true)
	private String name;

	public Ingredient() {
		super();
	}

	public Ingredient(String name) {
		super();
		this.name = name;
	}

	public Ingredient(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
