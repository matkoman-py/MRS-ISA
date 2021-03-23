package pharmacyhub.repositories;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

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
		drug.setId(randomStringGenerator());
		boolean drugIdTaken = read(drug.getId()) != null;
		if(drugIdTaken) {
			return null;
		}
		drugs.add(drug);
		return drug;
	}
	
	private String randomStringGenerator() {
		byte[] array = new byte[7]; // length is bounded by 7
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	    return generatedString;
	}
}
