package pharmacyhub.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import pharmacyhub.domain.Ingrediant;

@Repository
public class IngrediantRepository {
	
	private ArrayList<Ingrediant> ingrediants;
	
	public IngrediantRepository() {
		seedIngrediants();
	}
	
	private void seedIngrediants() {
		ingrediants = new ArrayList<Ingrediant>();
		ingrediants.add(new Ingrediant("id1", "cellulose"));
		ingrediants.add(new Ingrediant("id2", "lactose monohydrate"));
		ingrediants.add(new Ingrediant("id3", "colloidal anhydrous silica"));
		ingrediants.add(new Ingrediant("id4", "magnesium stearate"));
	}
	
	public ArrayList<Ingrediant> readAll(){
		return ingrediants;
	}
}
