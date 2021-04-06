package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Employment;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.dto.AddDermatologistToDrugstoreDto;
import pharmacyhub.dto.DermatologistDto;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.EmploymentRepository;
import pharmacyhub.repositories.users.DermatologistRepository;

@Service
public class EmploymentService {
	
	@Autowired
	private EmploymentRepository employmentRepository;

	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired
	private DermatologistRepository dermatologistRepository;	
	
	public List<Dermatologist> getAllDermatologistsForDrugstore(String drugstoreId) {
		//Drugstore drugstore = drugstoreRepository.findById(drugstoreId).orElse(null);
		List<Employment> employments = employmentRepository.findByDrugstoreId(drugstoreId);
		List<Dermatologist> dermatologists = new ArrayList<Dermatologist>();
		for (Employment e : employments) {
			System.out.println();
			System.out.println(e.getDermatologist().getName());
			System.out.println();
			dermatologists.add(e.getDermatologist());
		}
		return dermatologists;
	}
	
	public DermatologistDto addDermatologistToDrugstore(AddDermatologistToDrugstoreDto requestDto) throws Exception {

		// TODO: dodati proveru za duplikate
		Employment employement = new Employment();

		Dermatologist dermatologist = (Dermatologist) dermatologistRepository
				.findByEmail(requestDto.getDermatologistEmail());
		if (dermatologist == null) {
			throw new Exception("No such dermatologist");
		}

		Drugstore drugstore = drugstoreRepository.findById(requestDto.getDrugstoreId()).orElse(null);
		if (drugstore == null) {
			throw new Exception("No such drugstore");
		}

		Employment duplicateEmployement = employmentRepository
				.findByDermatologistIdAndDrugstoreId(dermatologist.getId(), drugstore.getId());
		
		if (duplicateEmployement != null) {
			throw new Exception("Dermatologist already works for pharmacy");
		}

		employement.setDermatologist(dermatologist);
		employement.setDrugstore(drugstore);
		drugstore.getEmployements().add(employement);
		dermatologist.getEmployements().add(employement);
		employement.setWorkingHoursFrom(drugstore.getWorkingHoursFrom());
		employement.setWorkingHoursTo(drugstore.getWorkingHoursTo());

		employmentRepository.save(employement);
		dermatologistRepository.save(dermatologist);
		drugstoreRepository.save(drugstore);

		return new DermatologistDto(dermatologist);

	}
	
}
