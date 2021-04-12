package pharmacyhub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.dto.DermatologistAppointmentDto;
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
}
