package pharmacyhub.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.AbsenceRequest;
import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.domain.enums.AbsenceRequestStatus;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.dto.AbsenceRequestDto;
import pharmacyhub.dto.RequestRejectionDto;
import pharmacyhub.repositories.AbsenceRequestRepository;
import pharmacyhub.repositories.DermatologistAppointmentRepository;
import pharmacyhub.repositories.PharmacistAppointmentRepository;
import pharmacyhub.repositories.users.UserRepository;

@Service
public class AbsenceRequestService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AbsenceRequestRepository absenceRequestRepository;
	
	@Autowired
	UserNotificationService userNotificationService;
	
	@Autowired
	PharmacistAppointmentRepository pharmacistAppointmentRepository;
	
	@Autowired
	DermatologistAppointmentRepository dermatologistAppointmentRepository;
	
	public String createNewRequest(AbsenceRequestDto createAbsenceRequestDto) throws ParseException {
		List<AbsenceRequest> employeeRequests = absenceRequestRepository.findByEmployeeAndStatus(userRepository.findById(createAbsenceRequestDto.getEmployeeId()).orElse(null), AbsenceRequestStatus.Pending);
		if(employeeRequests.size() > 0) {
			return "You have a pending absence request! You can't create a new one!";
		}
		Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(createAbsenceRequestDto.getStartDate());
		Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(createAbsenceRequestDto.getEndDate());
		AbsenceRequest ar = new AbsenceRequest(createAbsenceRequestDto.getReason(),startDate,endDate,userRepository.findById(createAbsenceRequestDto.getEmployeeId()).orElse(null));
		absenceRequestRepository.save(ar);
		//System.out.println(createAbsenceRequestDto.getEmployeeId()+createAbsenceRequestDto.getReason());
		return "Success!";
	}

	public String approveRequest(String requestId) throws MessagingException {
		AbsenceRequest request = absenceRequestRepository.findById(requestId).orElse(null);
		// otkazi sve preglede u datom terminu i obavesti pacijente o tome
		List<PharmacistAppointment> appointments = pharmacistAppointmentRepository.findByPharmacistAndProcessed((Pharmacist)request.getEmployee(), false);
		for (PharmacistAppointment appointment : appointments) {
			if ((appointment.getDate().equals(request.getStartDate()) || appointment.getDate().after(request.getStartDate())) && (appointment.getDate().equals(request.getEndDate()) || appointment.getDate().before(request.getEndDate()))) {
				appointment.setAppointmentReport("Appointment canceled due to pharmacist absence.");
				appointment.setProcessed(true);
				pharmacistAppointmentRepository.save(appointment);
				userNotificationService.notifyPatientAboutCancelation(appointment);
			}
		}
		// oznaci kao prihvacen
		request.setStatus(AbsenceRequestStatus.Approved);
		absenceRequestRepository.save(request);
		// posalji mejl
		userNotificationService.notifyPharmacistAboutApproving(request);
		return "You succesfully approved absence request for " + request.getEmployee().getName() + " " + request.getEmployee().getSurname() + "!";
	}

	public String rejectRequest(RequestRejectionDto pharmacistRequestRejectionDto) throws MessagingException {
		AbsenceRequest request = absenceRequestRepository.findById(pharmacistRequestRejectionDto.getRequestId()).orElse(null);
		// oznaci kao odbijen
		request.setStatus(AbsenceRequestStatus.Rejected);
		request.setAdminComment(pharmacistRequestRejectionDto.getReasonOfRejection());
		absenceRequestRepository.save(request);
		// posalji mejl
		userNotificationService.notifyAboutRejection(request);
		return "You succesfully rejected absence request for " + request.getEmployee().getName() + " " + request.getEmployee().getSurname() + "!";
	}

	public String approveRequestForDermatologist(String requestId) throws MessagingException {
		AbsenceRequest request = absenceRequestRepository.findById(requestId).orElse(null);
		// otkazi sve preglede u datom terminu i obavesti pacijente o tome
		List<DermatologistAppointment> appointments = dermatologistAppointmentRepository.findByDermatologistAndProcessed((Dermatologist)request.getEmployee(), false);
		for (DermatologistAppointment appointment : appointments) {
			if ((appointment.getDate().equals(request.getStartDate()) || appointment.getDate().after(request.getStartDate())) && (appointment.getDate().equals(request.getEndDate()) || appointment.getDate().before(request.getEndDate()))) {
				appointment.setAppointmentReport("Appointment canceled due to dermatologist absence.");
				appointment.setProcessed(true);
				dermatologistAppointmentRepository.save(appointment);
				userNotificationService.notifyPatientAboutCancelation(appointment);
			}
		}
		// oznaci kao prihvacen
		request.setStatus(AbsenceRequestStatus.Approved);
		absenceRequestRepository.save(request);
		// posalji mejl
		userNotificationService.notifyDermatologistAboutApproving(request);
		return "You succesfully approved absence request for " + request.getEmployee().getName() + " " + request.getEmployee().getSurname() + "!";

	}

}
