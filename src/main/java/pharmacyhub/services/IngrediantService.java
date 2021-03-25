package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Ingrediant;
import pharmacyhub.repositories.IngrediantRepository;

@Service
public class IngrediantService {

	@Autowired
	private IngrediantRepository ingrediantRepository;
	
	public List<Ingrediant> findAll()
	{
		return ingrediantRepository.findAll();
	}
	
	public List<Ingrediant> seedIngrediants() {
		ingrediantRepository.save(new Ingrediant("id1", "cellulose"));
		ingrediantRepository.save(new Ingrediant("id2", "lactose monohydrate"));
		ingrediantRepository.save(new Ingrediant("id3", "colloidal anhydrous silica"));
		ingrediantRepository.save(new Ingrediant("id4", "magnesium stearate"));
		return findAll();
	}
	
}
