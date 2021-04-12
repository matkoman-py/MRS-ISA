package pharmacyhub.controllers;

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
import pharmacyhub.domain.PharmacistAppointment;
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
		//return new ResponseEntity<>("Vratio",HttpStatus.OK);
	}
}
