package pharmacyhub.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.repositories.DrugRepository;

@Service
public class DrugService {
	
	@Autowired
	private DrugRepository drugRepository;
	
	public ArrayList<Drug> readAll()
	{
		return drugRepository.readAll();
	}

}
