package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.repositories.DrugRepository;

@Service
public class DrugService{
	
	@Autowired
	private DrugRepository drugRepository;
	
	public List<Drug> findAll()
	{
		return drugRepository.findAll();
	}
	
	public Drug save(Drug drug)
	{
		return drugRepository.save(drug);
	}
	

}
