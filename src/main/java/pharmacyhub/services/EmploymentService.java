package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Employment;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.dto.AddDermatologistToDrugstoreDto;
import pharmacyhub.dto.DermatologistDto;
import pharmacyhub.dto.EmploymentDto;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.EmploymentRepository;
import pharmacyhub.repositories.users.DermatologistRepository;
import pharmacyhub.repositories.users.PharmacistRepository;

@Service
public class EmploymentService {
	
	@Autowired
	private EmploymentRepository employmentRepository;

	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired
	private DermatologistRepository dermatologistRepository;	
	
	@Autowired
	private PharmacistRepository pharmacistRepository;
	
	public List<Dermatologist> getAllDermatologistsForDrugstore(String drugstoreId) {
		List<Employment> employments = employmentRepository.findByDrugstoreId(drugstoreId);
		List<Dermatologist> dermatologists = new ArrayList<Dermatologist>();
		for (Employment e : employments) {
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

	public List<EmploymentDto> getAllDermatologistsEmploymentsForDrugstore(String drugstoreId) {
		List<Employment> employments = employmentRepository.findByDrugstoreId(drugstoreId);
		List<EmploymentDto> employmentInfo = new ArrayList<EmploymentDto>();
		for (Employment e : employments) {
			employmentInfo.add(new EmploymentDto(e.getDermatologist().getName(), e.getDermatologist().getSurname(), e.getWorkingHoursFrom(), e.getWorkingHoursTo()));
		}
		return employmentInfo;
	}
	
	public List<EmploymentDto> getAllPharmacistsEmploymentsForDrugstore(String drugstoreId) {
		List<Pharmacist> pharmacists = pharmacistRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null));
		List<EmploymentDto> employmentInfo = new ArrayList<EmploymentDto>();
		for (Pharmacist p : pharmacists) {
			employmentInfo.add(new EmploymentDto(p.getName(), p.getSurname(), p.getWorkingHoursFrom(), p.getWorkingHoursTo()));
		}
		return employmentInfo;
	}
	
}
