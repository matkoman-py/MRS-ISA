package pharmacyhub.controllers;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.dto.DermatologistAppointmentPatientDto;
import pharmacyhub.dto.PharmacistAppointmentPatientDto;
import pharmacyhub.services.DermatologistAppointmentService;
import pharmacyhub.services.PharmacistAppointmentService;

@Controller
@RequestMapping("/pharmacist-appointment")
public class PharmacistAppointmentController {

	@Autowired
	private PharmacistAppointmentService pharmacistAppointmentService;
	
	@PostMapping(path ="/with-patient",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PharmacistAppointment> getAllPharmacistsForDrugstore(@RequestBody PharmacistAppointmentPatientDto pharmacistAppointmentDto) throws Exception {
		return new ResponseEntity<>(pharmacistAppointmentService.saveWithPatient(pharmacistAppointmentDto), HttpStatus.OK);
	}
	
	@GetMapping(path ="/begin-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PharmacistAppointment> getPharmacistAppointment(@RequestParam (value = "pharmacistAppointmentId", required=false,  defaultValue = "0") String pharmacistAppointmentId) throws Exception {
		return new ResponseEntity<>(pharmacistAppointmentService.findAppointment(pharmacistAppointmentId), HttpStatus.OK);
	}
	
	@GetMapping(path ="/get-drugstores", produces = MediaType.APPLICATION_JSON_VALUE)
	public /*ResponseEntity<Integer>*/ResponseEntity<Collection<Drugstore>> getDrugstores(@RequestParam (value = "pharmacistAppointmentDate", required=false,  defaultValue = "0") String pharmacistAppointmentDate,
															   @RequestParam (value = "pharmacistAppointmentTime", required=false,  defaultValue = "0") String pharmacistAppointmentTime) throws Exception {
		return new ResponseEntity<>(pharmacistAppointmentService.findDrugstores(pharmacistAppointmentTime,pharmacistAppointmentDate), HttpStatus.OK);
	}
	
	@GetMapping(path ="/get-pharmacists", produces = MediaType.APPLICATION_JSON_VALUE)
	public /*ResponseEntity<Integer>*/ResponseEntity<Collection<Pharmacist>> getPharmacists(@RequestParam (value = "drugstoreId", required=false,  defaultValue = "0") String drugstoreId,
			@RequestParam (value = "pharmacistAppointmentDate", required=false,  defaultValue = "0") String pharmacistAppointmentDate,
			   @RequestParam (value = "pharmacistAppointmentTime", required=false,  defaultValue = "0") String pharmacistAppointmentTime) throws Exception {
		return new ResponseEntity<>(pharmacistAppointmentService.findPharmacists(drugstoreId,pharmacistAppointmentDate,pharmacistAppointmentTime), HttpStatus.OK);
	}
	
	@GetMapping(path ="/get-appointments", produces = MediaType.APPLICATION_JSON_VALUE)
	public /*ResponseEntity<Integer>*/ResponseEntity<Collection<PharmacistAppointment>> getAppointments(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam (value = "patientId", required=false,  defaultValue = "0") String patientId) throws Exception {
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(pharmacistAppointmentService.getAppointments(patientId, pageable), HttpStatus.OK);
	}
	
	@GetMapping(path ="/get-appointments-length", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getAppointmentsLength(
			@RequestParam (value = "patientId", required=false,  defaultValue = "0") String patientId) throws Exception {
		return new ResponseEntity<>(pharmacistAppointmentService.getAppointmentsLength(patientId), HttpStatus.OK);
	}
	
	@GetMapping(path ="/all-appointments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PharmacistAppointment>> getAllPharmacistAppointments(@RequestParam (value = "pharmacistId", required=false,  defaultValue = "0") String pharmacistId) throws Exception {
		return new ResponseEntity<>(pharmacistAppointmentService.getAllPharmacistAppointments(pharmacistId), HttpStatus.OK);
	}
	
	@GetMapping(path ="/end-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PharmacistAppointment> endAppointment(
			@RequestParam (value = "pharmacistAppointmentId", required=false,  defaultValue = "0") String appointmentId,
			@RequestParam (value = "appointmentReport", required=false,  defaultValue = "0") String appointmentReport) throws Exception {
		return new ResponseEntity<>(pharmacistAppointmentService.endAppointment(appointmentId,appointmentReport), HttpStatus.OK);
	}
	
	@GetMapping(path ="/all-pharm-done", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PharmacistAppointment>> getAllAPharmacistAppointmentsDone(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam (value = "pharmacistId", required=false,  defaultValue = "0") String pharmacistId) throws Exception {
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(pharmacistAppointmentService.findAllPharmacistAppointmentsDone(pharmacistId,pageable), HttpStatus.OK);
	}
	
	@GetMapping(path ="/all-pharm-done-length", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getAllADermatologistAppointmentsDoneLength(
			
			@RequestParam (value = "pharmacistId", required=false,  defaultValue = "0") String pharmacistId) throws Exception {
		
		return new ResponseEntity<>(pharmacistAppointmentService.findAllPharmacistAppointmentsDoneLength(pharmacistId), HttpStatus.OK);
	}
	
	@GetMapping(path ="/all-pharm-todo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PharmacistAppointment>> getAllAPharmacistAppointmentsTodo(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam (value = "pharmacistId", required=false,  defaultValue = "0") String pharmacistId) throws Exception {
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(pharmacistAppointmentService.findAllPharmacistAppointmentsTodo(pharmacistId,pageable), HttpStatus.OK);
	}
	
	@GetMapping(path ="/all-pharm-todo-length", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getAllAPharmacistAppointmentsTodoLength(
			
			@RequestParam (value = "pharmacistId", required=false,  defaultValue = "0") String pharmacistId) throws Exception {
		
		return new ResponseEntity<>(pharmacistAppointmentService.findAllPharmacistAppointmentsTodoLength(pharmacistId), HttpStatus.OK);
	}
}
