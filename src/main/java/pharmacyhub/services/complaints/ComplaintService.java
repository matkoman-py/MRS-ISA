package pharmacyhub.services.complaints;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.complaints.Complaint;
import pharmacyhub.domain.users.ComplaintType;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.User;
import pharmacyhub.dto.complaint.ComplaintDto;
import pharmacyhub.dto.complaint.MakeComplaintDto;
import pharmacyhub.repositories.ComplaintRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.users.PatientRepository;
import pharmacyhub.repositories.users.UserRepository;

@Service
public class ComplaintService {

	@Autowired
	private ComplaintRepository complaintRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	private ComplaintDto toComplaintDto(Complaint complaint) {
		ComplaintDto complaintDto = new ComplaintDto();
		
		complaintDto.setId(complaint.getId());
		complaintDto.setPatientId(complaint.getPatient().getEmail());
		complaintDto.setPatientEmail(complaint.getPatient().getEmail());
		
		switch(complaint.getType()) {
		case Drugstore:
			complaintDto.setDrugstoreId(complaint.getDrugstore().getId());
			complaintDto.setDrugstoreName(complaint.getDrugstore().getName());
			break;
		default:
			complaintDto.setEmployeeId(complaint.getEmployee().getId());
			complaintDto.setEmployeName(complaint.getEmployee().getName());
			complaintDto.setEmployeSurname(complaint.getEmployee().getSurname());
			complaintDto.setDrugstoreId(complaint.getDrugstore().getId());
			complaintDto.setDrugstoreName(complaint.getDrugstore().getName());
			break;
		}
		return complaintDto;
	}
	
	public List<ComplaintDto> getComplaints(){
		return complaintRepository.findAll().stream()
				.map(complaint -> toComplaintDto(complaint))
				.collect(Collectors.toList());
	}
	
	public ComplaintDto makeComplaint(MakeComplaintDto makeComplaintDto) {
				
		Patient patient = patientRepository.findById(makeComplaintDto.getPatientId()).orElse(null);
		Drugstore drugstore = drugstoreRepository.findById(makeComplaintDto.getDrugstoreId()).orElse(null);

		Complaint complaint = new Complaint();
		complaint.setDrugstore(drugstore);
		complaint.setPatient(patient);
		complaint.setType(makeComplaintDto.getType());
		complaint.setText(makeComplaintDto.getText());
		
		if(complaint.getType() != ComplaintType.Drugstore) {
			User employee = userRepository.findById(makeComplaintDto.getEmployeeId()).orElse(null);
			complaint.setEmployee(employee);
		}
		
		complaint = complaintRepository.save(complaint);
		
		return toComplaintDto(complaint);
	}
}
