package pharmacyhub.domain;

import javax.persistence.Entity;

@Entity
public class Ingrediant extends BaseEntity{

	private String name;

	public Ingrediant() {
		super();
	}

	public Ingrediant(String name) {
		super();
		this.name = name;
	}

	public Ingrediant(String id, String name) {
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
