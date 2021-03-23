package pharmacyhub.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DrugType;
import pharmacyhub.repositories.DrugTypeRepository;

@Service
public class DrugTypeService {

	@Autowired
	private DrugTypeRepository drugTypeRepository;
	
	public ArrayList<DrugType> readAll()
	{
		return drugTypeRepository.readAll();
	}
	
	public DrugType read(String id)
	{
		return drugTypeRepository.read(id);
	}
	
}
