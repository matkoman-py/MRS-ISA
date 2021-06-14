package pharmacyhub.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.AbsenceRequest;
import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Employment;
import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.domain.enums.AbsenceRequestStatus;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.dto.AddDermatologistToDrugstoreDto;
import pharmacyhub.dto.DermatologistAbsenceRequestDto;
import pharmacyhub.dto.DermatologistAppointmentCreationDto;
import pharmacyhub.dto.DermatologistDto;
import pharmacyhub.dto.EmploymentDrugstoreDto;
import pharmacyhub.dto.EmploymentDto;
import pharmacyhub.dto.HireDermatologistDto;
import pharmacyhub.dto.PharmacistAbsenceRequestDto;
import pharmacyhub.dto.RemoveDermatologistDto;
import pharmacyhub.repositories.AbsenceRequestRepository;
import pharmacyhub.repositories.DermatologistAppointmentRepository;
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
	
	@Autowired
	private DermatologistAppointmentRepository dermatologistAppointmentRepository;
	
	@Autowired
	private AbsenceRequestRepository abensceRequestRepository;
	
	public List<DermatologistAppointmentCreationDto> getAllDermatologistsForDrugstore(String drugstoreId) {
		List<Employment> employments = employmentRepository.findByDrugstoreId(drugstoreId);
		List<DermatologistAppointmentCreationDto> returnValue = new ArrayList<DermatologistAppointmentCreationDto>();
		for (Employment e : employments) {
			returnValue.add(new DermatologistAppointmentCreationDto(e.getDermatologist(), e.getWorkingHoursFrom(), e.getWorkingHoursTo()));
		}
		return returnValue;
	}
	
	public List<Dermatologist> getNotEmployedDermatologists(String drugstoreId) {
		List<Employment> employments = employmentRepository.findAll();
		HashMap<String, Dermatologist> employedDermatologists = new HashMap<String, Dermatologist>();
		List<Dermatologist> returnValue = new ArrayList<Dermatologist>();
		for (Employment e : employments) {
			if (e.getDrugstore().getId().equals(drugstoreId))
				employedDermatologists.put(e.getDermatologist().getId(), e.getDermatologist());
		}
		for (Dermatologist dermatologist : dermatologistRepository.findAll()) {
			if (!employedDermatologists.containsKey(dermatologist.getId())) {
				returnValue.add(dermatologist);
			}
		}
		return returnValue;
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
	
	public String hireDermatologist(HireDermatologistDto requestDto) throws Exception {

		// TODO: dodati proveru za duplikate
		Employment employement = new Employment();

		System.out.println("ISPIS: " + requestDto.getDermatologistId() + "  " + requestDto.getDrugstoreId() + "  " + requestDto.getWorkingHoursFrom() + "  " + requestDto.getWorkingHoursTo());
		Dermatologist dermatologist = (Dermatologist) dermatologistRepository
				.findById(requestDto.getDermatologistId()).orElse(null);
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
		employement.setWorkingHoursFrom(requestDto.getWorkingHoursFrom());
		employement.setWorkingHoursTo(requestDto.getWorkingHoursTo());

		employmentRepository.save(employement);
		dermatologistRepository.save(dermatologist);
		drugstoreRepository.save(drugstore);

		return "Success";

	}
	
	/*public DermatologistDto addDermatologistToDrugstore(AddDermatologistToDrugstoreDto requestDto) throws Exception {

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

	}*/

	public List<EmploymentDto> getAllDermatologistsEmploymentsForDrugstore(String drugstoreId) {
		List<Employment> employments = employmentRepository.findByDrugstoreId(drugstoreId);
		List<EmploymentDto> employmentInfo = new ArrayList<EmploymentDto>();
		for (Employment e : employments) {
			employmentInfo.add(new EmploymentDto(e.getDermatologist().getId(),e.getDermatologist().getName(), e.getDermatologist().getSurname(), e.getWorkingHoursFrom(), e.getWorkingHoursTo()));
		}
		return employmentInfo;
	}
	
	public List<EmploymentDto> getAllPharmacistsEmploymentsForDrugstore(String drugstoreId) {
		List<Pharmacist> pharmacists = pharmacistRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null));
		List<EmploymentDto> employmentInfo = new ArrayList<EmploymentDto>();
		for (Pharmacist p : pharmacists) {
			employmentInfo.add(new EmploymentDto(p.getId(),p.getName(), p.getSurname(), p.getWorkingHoursFrom(), p.getWorkingHoursTo()));
		}
		return employmentInfo;
	}
	
	public List<EmploymentDrugstoreDto> getAllDermatologistEmployments(String dermatologistId) {
		
		List<Employment> employments = employmentRepository.findByDermatologistId(dermatologistId);
		List<EmploymentDrugstoreDto> employmentInfo = new ArrayList<EmploymentDrugstoreDto>();
		for (Employment e : employments) {
			employmentInfo.add(new EmploymentDrugstoreDto(e.getDrugstore().getName(),e.getDermatologist().getName(), e.getDermatologist().getSurname(), e.getWorkingHoursFrom(), e.getWorkingHoursTo()));
			System.out.println(e.getDermatologist().getName()+" "+ e.getDrugstore().getName());
		}
		
		return employmentInfo;
	}
	
	public String removeDermatologistFromDrugstore(RemoveDermatologistDto info) throws Exception {
		if (checkFutureDermatologistAppointments(info.getDermatologistEmail())) {
			System.out.println("NIJE DOZVOLJENOO");
			return "Denied";
		}
		Dermatologist dermatologist = (Dermatologist)dermatologistRepository.findByEmail(info.getDermatologistEmail());
		Drugstore drugstore = drugstoreRepository.findById(info.getDrugstoreId()).orElse(null);
		employmentRepository.deleteByDermatologistAndDrugstore(dermatologist, drugstore);
		return "Success";
	}
	
	private boolean checkFutureDermatologistAppointments(String dermatologistEmail) {
		List<DermatologistAppointment> appointments = dermatologistAppointmentRepository.findByDermatologistEmailAndProcessedFalseAndPatientNotNull(dermatologistEmail);
		for (DermatologistAppointment appointment : appointments) {
			if (appointment.getDate().after(new Date())) {
				return true;
			}
		}
		return false;
		//dermatologistAppointmentRepository.deleteByDermatologist((Dermatologist)dermatologistRepository.findByEmail(dermatologistEmail)); // treba samo buduce
	}

	public List<DermatologistAbsenceRequestDto> getDermatologistAbsenceRequests() {
		List<Dermatologist> dermatologists = dermatologistRepository.findAll();
		List<DermatologistAbsenceRequestDto> requests = new ArrayList<DermatologistAbsenceRequestDto>();
		for (Dermatologist dermatologist : dermatologists) {
			List<AbsenceRequest> requestsForCurrentDermatologist = abensceRequestRepository.findByEmployeeAndStatus(dermatologist, AbsenceRequestStatus.Pending);
			List<Employment> employments = employmentRepository.findByDermatologistId(dermatologist.getId());
			if (requestsForCurrentDermatologist.size() > 0 && employments.size() > 0) {
				String drugstores = "";
				for (Employment employment : employments) {
					drugstores += "'" + employment.getDrugstore().getName() + "', ";
				}
				requests.add(new DermatologistAbsenceRequestDto(dermatologist.getName(), dermatologist.getSurname(), requestsForCurrentDermatologist.get(0).getStartDate(), requestsForCurrentDermatologist.get(0).getEndDate(), requestsForCurrentDermatologist.get(0).getReason(), requestsForCurrentDermatologist.get(0).getId(), drugstores.substring(0, drugstores.length() - 2)));
			}
		}
		return requests;
	}
}
