package pharmacyhub.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.domain.Drug;

@Repository
public class DrugRepository {
	
	private ArrayList<Drug> drugs;
	
	public DrugRepository() {
		drugs = new ArrayList<Drug>();
	}
	
	public ArrayList<Drug> readAll() {
		return drugs;
	}
}
