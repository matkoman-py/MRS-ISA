package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.domain.users.Patient;
import pharmacyhub.services.PatientService;

@Controller
@RequestMapping("/get_patients")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Patient>> getGreetings() {
		return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "fill", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Patient>> fill() {
		return new ResponseEntity<>(patientService.fill(), HttpStatus.OK);
	}
}
