package pharmacyhub.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import pharmacyhub.domain.DrugType;

@Repository
public class DrugTypeRepository {
	
	private ArrayList<DrugType> drugTypes;
	
	public DrugTypeRepository() {
		seedDrugTypes();
	}
	
	private void seedDrugTypes() {
		drugTypes = new ArrayList<DrugType>();
		drugTypes.add(new DrugType("id1", "antidepressant", "They help to improve your sleep, energy, appetite, mood and negative thoughts about yourself."));
		drugTypes.add(new DrugType("id2", "antipsychotic", "They help with hallucinations and delusions, anxiety, agitation and problems with mood, thinking and socialising."));
		drugTypes.add(new DrugType("id3", "benzodiazepines", "Benzodiazepines are sometimes used to treat anxiety and insomnia."));
		drugTypes.add(new DrugType("id4", "stimulant", "They help to improve attention, concentration and alertness."));
	}
	
	public ArrayList<DrugType> readAll(){
		return drugTypes;
	}
	
	public DrugType read(String id){
		return drugTypes.stream()
				.filter(drugType -> drugType.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
}