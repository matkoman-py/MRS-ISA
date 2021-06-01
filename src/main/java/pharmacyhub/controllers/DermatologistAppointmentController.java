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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.DrugReservation;
import pharmacyhub.dto.DermatologistAppointmentDto;
import pharmacyhub.dto.DermatologistAppointmentPatientDto;
import pharmacyhub.dto.search.DrugReservationCancelDto;
import pharmacyhub.services.DermatologistAppointmentService;

@Controller
@RequestMapping("/dermatologist-appointment")
public class DermatologistAppointmentController {
	
	@Autowired
	private DermatologistAppointmentService dermatologistAppointmentService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DermatologistAppointment> getAllDermatologistsForDrugstore(@RequestBody DermatologistAppointmentDto dermatologistAppointmentDto) throws Exception {
		return new ResponseEntity<>(dermatologistAppointmentService.save(dermatologistAppointmentDto), HttpStatus.OK);
	}

	
	@PostMapping(path ="/with-patient",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DermatologistAppointment> getAllDermatologistsForDrugstore(@RequestBody DermatologistAppointmentPatientDto dermatologistAppointmentDto) throws Exception {
		return new ResponseEntity<>(dermatologistAppointmentService.saveWithPatient(dermatologistAppointmentDto), HttpStatus.OK);
	}
	
	@GetMapping(path ="/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistAppointment>> getAllDermatologistAppointments(@RequestParam (value = "drugstoreId", required=false,  defaultValue = "0") String drugstoreId) throws Exception {
		return new ResponseEntity<>(dermatologistAppointmentService.findAvailable(drugstoreId), HttpStatus.OK);
	}
	
	@GetMapping(path ="/createReserrvation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistAppointment>> createReservation(@RequestParam (value = "patientId", required=false,  defaultValue = "0") String patientId,
      @RequestParam (value = "appointmentId", required=false,  defaultValue = "0") String appointmentId,
			@RequestParam (value = "drugstoreId", required=false,  defaultValue = "0") String drugstoreId) throws Exception {
		  return new ResponseEntity<>(dermatologistAppointmentService.createReservation(patientId, appointmentId,drugstoreId), HttpStatus.OK);	
	}
	
	@GetMapping(path ="/returnAppointments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistAppointment>> returnAppointments(@RequestParam (value = "patientId", required=false,  defaultValue = "0") String patientId,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) throws Exception {
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);

		  return new ResponseEntity<>(dermatologistAppointmentService.returnAppointments(patientId, pageable), HttpStatus.OK);	
	}
	//reservations-length
	@GetMapping(path ="/reservations-length", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> reservationsLength(@RequestParam (value = "patientId", required=false,  defaultValue = "0") String patientId) throws Exception {
		return new ResponseEntity<>(dermatologistAppointmentService.reservationsLength(patientId), HttpStatus.OK);	
	}
	
	@GetMapping(path ="/begin-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DermatologistAppointment> getDermatologistAppointment(@RequestParam (value = "dermatologistAppointmentId", required=false,  defaultValue = "0") String dermatologistAppointmentId) throws Exception {
		return new ResponseEntity<>(dermatologistAppointmentService.findAppointment(dermatologistAppointmentId), HttpStatus.OK);
	}
	
	@GetMapping(path ="/available", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistAppointment>> getAllAvailableDermatologistAppointments(@RequestParam (value = "drugstoreId", required=false,  defaultValue = "0") String drugstoreId,
			@RequestParam (value = "dermatologistId", required=false,  defaultValue = "0") String dermatologistId) throws Exception {
		return new ResponseEntity<>(dermatologistAppointmentService.findAvailable(drugstoreId,dermatologistId), HttpStatus.OK);
	}
	
	@GetMapping(path ="/reserveAppointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistAppointment>> reserveAppointment(@RequestParam (value = "drugstoreId", required=false,  defaultValue = "0") String drugstoreId,
			@RequestParam (value = "patientId", required=false,  defaultValue = "0") String patientId,
			@RequestParam (value = "appointmentId", required=false,  defaultValue = "0") String appointmentId) throws Exception {
		  return new ResponseEntity<>(dermatologistAppointmentService.reserveAppointment( drugstoreId,patientId,appointmentId), HttpStatus.OK);
	}
	
	@GetMapping(path ="/all-derm-app", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistAppointment>> getAllADermatologistAppointments(
			@RequestParam (value = "dermatologistId", required=false,  defaultValue = "0") String dermatologistId) throws Exception {
		return new ResponseEntity<>(dermatologistAppointmentService.findAllDermatologistAppointments(dermatologistId), HttpStatus.OK);
	}
	
	@GetMapping(path ="/end-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DermatologistAppointment> endAppointment(
			@RequestParam (value = "dermatologistAppointmentId", required=false,  defaultValue = "0") String appointmentId,
			@RequestParam (value = "appointmentReport", required=false,  defaultValue = "0") String appointmentReport) throws Exception {
		return new ResponseEntity<>(dermatologistAppointmentService.endAppointment(appointmentId,appointmentReport), HttpStatus.OK);
	}
	
	@GetMapping(path ="/all-derm-done", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistAppointment>> getAllADermatologistAppointmentsDone(
			@RequestParam (value = "dermatologistId", required=false,  defaultValue = "0") String dermatologistId) throws Exception {
		return new ResponseEntity<>(dermatologistAppointmentService.findAllDermatologistAppointmentsDone(dermatologistId), HttpStatus.OK);
	}
	
	@GetMapping(path = "/cancelAppointment",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistAppointment>> cancelReservation(@RequestParam (value = "dermatologistAppointmentId", required=false,  defaultValue = "0") String appointmentId) throws Exception {

		return new ResponseEntity<>(dermatologistAppointmentService.cancelAppointment(appointmentId), HttpStatus.OK);
	}
}
