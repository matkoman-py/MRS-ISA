package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DrugType;
import pharmacyhub.repositories.DrugTypeRepository;

@Service
public class DrugTypeService {

	@Autowired
	private DrugTypeRepository drugTypeRepository;
	
	public List<DrugType> findAll()
	{
		return drugTypeRepository.findAll();
	}
	
	public DrugType findById(String id)
	{
		return drugTypeRepository.findById(id).orElse(null);
	}
	
	public List<DrugType> seed() {
		drugTypeRepository.save(new DrugType("antidepressant", "They help to improve your sleep, energy, appetite, mood and negative thoughts about yourself."));
		drugTypeRepository.save(new DrugType("antipsychotic", "They help with hallucinations and delusions, anxiety, agitation and problems with mood, thinking and socialising."));
		drugTypeRepository.save(new DrugType("benzodiazepines", "Benzodiazepines are sometimes used to treat anxiety and insomnia."));
		drugTypeRepository.save(new DrugType("stimulant", "They help to improve attention, concentration and alertness."));
		return drugTypeRepository.findAll();
	}
	
}
