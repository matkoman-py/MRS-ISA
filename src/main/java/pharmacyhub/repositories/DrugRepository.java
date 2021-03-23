package pharmacyhub.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import pharmacyhub.domain.Drug;

@Repository
public class DrugRepository{
	
	private ArrayList<Drug> drugs;
	
	public DrugRepository() {
		drugs = new ArrayList<Drug>();
	}
	
	public ArrayList<Drug> readAll() {
		return drugs;
	}
	
	public Drug read(String id) {
		return drugs.stream()
				.filter(drug -> drug.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
	
	public Drug add(Drug drug) {
		boolean drugIdTaken = read(drug.getId()) != null;
		if(drugIdTaken) {
			return null;
		}
		drugs.add(drug);
		return drug;
	}
}
