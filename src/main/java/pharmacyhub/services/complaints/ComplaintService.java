package pharmacyhub.services.complaints;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.DrugReservation;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.domain.complaints.Complaint;
import pharmacyhub.domain.complaints.Reply;
import pharmacyhub.domain.enums.ComplaintType;
import pharmacyhub.domain.enums.UserType;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.User;
import pharmacyhub.dto.complaint.ComplaintDto;
import pharmacyhub.dto.complaint.MakeComplaintDto;
import pharmacyhub.dto.complaint.MakeReplyDto;
import pharmacyhub.dto.complaint.ReplyDto;
import pharmacyhub.repositories.ComplaintRepository;
import pharmacyhub.repositories.DermatologistAppointmentRepository;
import pharmacyhub.repositories.DrugReservationRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.PharmacistAppointmentRepository;
import pharmacyhub.repositories.ReplyRepository;
import pharmacyhub.repositories.users.PatientRepository;
import pharmacyhub.repositories.users.UserRepository;
import pharmacyhub.services.UserNotificationService;

@Service
public class ComplaintService {

	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Autowired
	private ReplyRepository replyRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DrugstoreRepository drugstoreRepository;

	@Autowired
	private DermatologistAppointmentRepository dermatologistAppointmentRepository;
	
	@Autowired
	private PharmacistAppointmentRepository pharmacistAppointmentRepository;
	
	@Autowired
	private DrugReservationRepository drugReservationRepository;
	
	@Autowired
	private UserNotificationService userNotificationService;
	
	private ComplaintDto toComplaintDto(Complaint complaint) {
		ComplaintDto complaintDto = new ComplaintDto();
		
		complaintDto.setId(complaint.getId());
		complaintDto.setPatientId(complaint.getPatient().getEmail());
		complaintDto.setPatientEmail(complaint.getPatient().getEmail());
		complaintDto.setText(complaint.getText());
		complaintDto.setType(complaint.getType());
		complaintDto.setHasReply(complaint.isHasReply());
		
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
	
	public List<ComplaintDto> getComplaints(Pageable pageable){
		return complaintRepository.findAll(pageable).stream()
				.map(complaint -> toComplaintDto(complaint))
				.collect(Collectors.toList());
	}
	
	public List<ComplaintDto> getComplaintsByPatientId(String patientId, Pageable pageable){
		return complaintRepository.findByPatientId(patientId, pageable).stream()
				.map(complaint -> toComplaintDto(complaint))
				.collect(Collectors.toList());
	}
	
	public boolean patientHasDermatologistAppointment(String patientId, String dermatologistId) {
		List<DermatologistAppointment> appointments = dermatologistAppointmentRepository.findByPatientIdAndDermatologistIdAndProcessedTrue(patientId, dermatologistId);
		return appointments.size() > 0;
	}
	
	public boolean patientHasPharmacistAppointment(String patientId, String pharmacistId) {
		List<PharmacistAppointment> appointments = pharmacistAppointmentRepository.findByPatientIdAndPharmacistIdAndProcessedTrue(patientId, pharmacistId);
		return appointments.size() > 0;
	}
	
	public boolean patientHasPickedUpDrugInDrugsotre(String patientId, String drugstoreId) {
		List<DrugReservation> drugReservations = drugReservationRepository.findByDrugstoreIdAndPatientIdAndIssuedTrue(drugstoreId, patientId);
		return drugReservations.size() > 0;
	}
	
	public boolean patientHasInteractionWithDrugstore(String patientId, String drugstoreId) {
		boolean hasPharmacistAppointment = pharmacistAppointmentRepository.findByPatientIdAndPharmacistDrugstoreIdAndProcessedTrue(patientId, drugstoreId).size() > 0;
		boolean hasDermatologistAppointment = dermatologistAppointmentRepository.findByPatientIdAndDrugstoreIdAndProcessedTrue(patientId, drugstoreId).size() > 0;
		return hasPharmacistAppointment || hasDermatologistAppointment || patientHasPickedUpDrugInDrugsotre(patientId, drugstoreId);
	}
	
	public ComplaintDto makeComplaint(MakeComplaintDto makeComplaintDto) throws Exception {
				
		Patient patient = patientRepository.findById(makeComplaintDto.getPatientId()).orElse(null);
		Drugstore drugstore = drugstoreRepository.findById(makeComplaintDto.getDrugstoreId()).orElse(null);

		Complaint complaint = new Complaint();
		complaint.setDrugstore(drugstore);
		complaint.setPatient(patient);
		complaint.setType(makeComplaintDto.getType());
		complaint.setText(makeComplaintDto.getText());
		
		if(patient == null) {
			throw new Exception("Given patient doesn't exist!");
		}
		
		if(drugstore == null) {
			throw new Exception("Given drugstore doesn't exist!");
		}
		
		if(complaint.getType() != ComplaintType.Drugstore) {
			User employee = userRepository.findById(makeComplaintDto.getEmployeeId()).orElse(null);
			complaint.setEmployee(employee);
			
			if(complaint.getType() == ComplaintType.Dermatologist && !patientHasDermatologistAppointment(patient.getId(), employee.getId())) {
				throw new Exception("Patient has no appoiontments with given dermatologist!");
			}
			
			if(complaint.getType() == ComplaintType.Pharmacist && !patientHasPharmacistAppointment(patient.getId(), employee.getId())) {
				throw new Exception("Patient has no appoiontments with given pharmacist!");
			}
		}else if(!patientHasInteractionWithDrugstore(makeComplaintDto.getPatientId(), makeComplaintDto.getDrugstoreId())) {
			throw new Exception("Patient has no interactions with given drugstore!");
		}
		
		complaint = complaintRepository.save(complaint);
		
		return toComplaintDto(complaint);
	}

	
	public ReplyDto getReply(String complaintId) throws Exception {
		Complaint complaint = complaintRepository.findById(complaintId).orElse(null);
		
		if(complaint == null) {
			throw new Exception("The given complaint doesn't exist!");
		}
		Reply reply = replyRepository.findByComplaint(complaint);
		return (reply != null) ? new ReplyDto(reply) : null;
	}
	
	public ReplyDto makeReply(MakeReplyDto makeReplyDto) throws Exception {
		Reply alreadyGivenReply = replyRepository.findById(makeReplyDto.getComplaintId()).orElse(null);
		Complaint complaint = complaintRepository.findById(makeReplyDto.getComplaintId()).orElse(null);
		User user = userRepository.findById(makeReplyDto.getAdminId()).orElse(null);
		
		if(user == null || user.getType() != UserType.SystemAdmin) {
			throw new Exception("The given system admin doesn't exist!");
		}
		
		if(complaint == null) {
			throw new Exception("The given complaint doesn't exist!");
		}
		
		if (alreadyGivenReply != null) {
			throw new Exception("Reply already given!");
		}
		complaint.setHasReply(true);
		complaintRepository.save(complaint);
		
		Reply reply = new Reply(complaint, user, makeReplyDto.getText());
		userNotificationService.notifyAboutComplaintReply(complaint, reply);
		return new ReplyDto(replyRepository.save(reply));
	}
}
