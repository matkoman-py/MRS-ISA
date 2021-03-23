package pharmacyhub.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Ingrediant;
import pharmacyhub.repositories.IngrediantRepository;

@Service
public class IngrediantService {

	@Autowired
	private IngrediantRepository ingrediantRepository;
	
	public ArrayList<Ingrediant> readAll()
	{
		return ingrediantRepository.readAll();
	}
	
}
