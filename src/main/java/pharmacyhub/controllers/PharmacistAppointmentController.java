package pharmacyhub.controllers;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
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
	public /*ResponseEntity<Integer>*/ResponseEntity<Collection<PharmacistAppointment>> getAppointments(@RequestParam (value = "patientId", required=false,  defaultValue = "0") String patientId) throws Exception {
		return new ResponseEntity<>(pharmacistAppointmentService.getAppointments(patientId), HttpStatus.OK);
  }
  
	@GetMapping(path ="/all-appointments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PharmacistAppointment>> getAllPharmacistAppointments(@RequestParam (value = "pharmacistId", required=false,  defaultValue = "0") String pharmacistId) throws Exception {
		return new ResponseEntity<>(pharmacistAppointmentService.getAllPharmacistAppointments(pharmacistId), HttpStatus.OK);
	}
}
