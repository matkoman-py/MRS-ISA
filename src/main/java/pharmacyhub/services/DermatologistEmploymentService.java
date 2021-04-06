package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DermatologistEmployment;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.repositories.DermatologistEmploymentRepository;
import pharmacyhub.repositories.DrugstoreRepository;

@Service
public class DermatologistEmploymentService {
	
	@Autowired 
	private DermatologistEmploymentRepository dermatologistEmploymentRepository;
	
	@Autowired 
	private DrugstoreRepository drugstoreRepository;

	public List<Dermatologist> getAllDermatologistsForDrugstore(String drugstoreId) {
		Drugstore drugstore = drugstoreRepository.findById(drugstoreId).orElse(null);
		List<DermatologistEmployment> dermatologistEmployments = dermatologistEmploymentRepository.findByDrugstore(drugstore);
		List<Dermatologist> dermatologists = new ArrayList<Dermatologist>();
		for (DermatologistEmployment de : dermatologistEmployments) {
			dermatologists.add(de.getDermatologist());
		}
		return dermatologists;
	}
	
}
