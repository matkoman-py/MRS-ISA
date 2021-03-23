package pharmacyhub.domain;

public class Ingrediant {

	private String id;
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
}
