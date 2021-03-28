package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.repositories.DrugstoreRepository;

@Service
public class DrugstoreService {

	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	public List<Drugstore> findAll() {
		return drugstoreRepository.findAll();
	}

	public Drugstore save(Drugstore drugstore) throws Exception {

		//treba proveriti da li je validno
		return drugstoreRepository.save(drugstore);
	}
}
