package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.Employee;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.services.PatientService;

@Controller
@RequestMapping("/patients")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Patient>> getGreetings() {
		return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path ="/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Patient>> search(@RequestParam(value = "patientNameParam", required=false,  defaultValue = "0") String patientName,
													  @RequestParam(value = "patientSurnameParam", required=false,  defaultValue = "0") String patientSurname
												) throws Exception {
		
		return new ResponseEntity<>(patientService.returnPatients(patientName,patientSurname), HttpStatus.OK);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Patient>> update(@RequestBody Patient patient) throws Exception {
		return new ResponseEntity<>(patientService.update(patient), HttpStatus.OK);

	}
}
