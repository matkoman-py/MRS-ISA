package pharmacyhub.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "UPDATE ingredient SET deleted = true WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class Ingredient extends BaseEntity {

	@Column(nullable = false, unique=true)
	private String name;
	
	@ManyToMany(mappedBy="ingredients")
	private List<Drug> drugs;

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
