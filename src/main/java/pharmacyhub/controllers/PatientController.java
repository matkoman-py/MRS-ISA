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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.domain.users.Patient;
import pharmacyhub.services.PatientService;

@Controller
@RequestMapping("/patients")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Patient>> getGreetings(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size
			) {
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(patientService.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping(path ="/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Patient>> search(@RequestParam(value = "page", required = false) Integer page,
													  @RequestParam(value = "size", required = false) Integer size,
													  @RequestParam(value = "patientNameParam", required=false,  defaultValue = "") String patientName,
													  @RequestParam(value = "patientSurnameParam", required=false,  defaultValue = "") String patientSurname
												) throws Exception {
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(patientService.returnPatients(pageable,patientName,patientSurname), HttpStatus.OK);
	}
	
	@GetMapping(path ="/searchLength", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> searchLength(
													  @RequestParam(value = "patientNameParam", required=false,  defaultValue = "") String patientName,
													  @RequestParam(value = "patientSurnameParam", required=false,  defaultValue = "") String patientSurname
												) throws Exception {
		
		return new ResponseEntity<>(patientService.returnPatientsLength(patientName,patientSurname), HttpStatus.OK);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> update(@RequestBody Patient patient) throws Exception {
		return new ResponseEntity<>(patientService.update(patient), HttpStatus.OK);

	}
	
	@GetMapping(path ="/id", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> profile(@RequestParam(value = "patientId", required=false,  defaultValue = "0") String patientId) throws Exception {
		
		return new ResponseEntity<>(patientService.returnPatient(patientId), HttpStatus.OK);
	}
	
	@GetMapping(path ="/penalty", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> penalty(@RequestParam(value = "patientId", required=false,  defaultValue = "0") String patientId) throws Exception {
		return new ResponseEntity<>(patientService.penalty(patientId), HttpStatus.OK);
	}
}
