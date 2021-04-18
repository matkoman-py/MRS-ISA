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

import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.dto.AddDermatologistToDrugstoreDto;
import pharmacyhub.dto.DermatologistDto;
import pharmacyhub.dto.EmploymentDrugstoreDto;
import pharmacyhub.dto.EmploymentDto;
import pharmacyhub.services.EmploymentService;

@Controller
@RequestMapping("/employment")
public class EmploymentController {
	
	@Autowired
	private EmploymentService employmentService;
		
	@PostMapping(path="/dermatologist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DermatologistDto> addDermatologistToDrugstore(
			@RequestBody AddDermatologistToDrugstoreDto requestDto) throws Exception {
		return new ResponseEntity<>(employmentService.addDermatologistToDrugstore(requestDto), HttpStatus.OK);

	}
		
	@GetMapping(path="/dermatologists", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Dermatologist>> getAllDermatologistsForDrugstore(@RequestParam(value = "drugstoreId") String drugstoreId) {
		return new ResponseEntity<>(employmentService.getAllDermatologistsForDrugstore(drugstoreId), HttpStatus.OK);
	}
	
	@GetMapping(path="/dermatologist-employments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<EmploymentDto>> getAllDermatologistsEmploymentsForDrugstore(@RequestParam(value = "drugstoreId") String drugstoreId) {
		return new ResponseEntity<>(employmentService.getAllDermatologistsEmploymentsForDrugstore(drugstoreId), HttpStatus.OK);
	}
	
	@GetMapping(path="/pharmacist-employments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<EmploymentDto>> getAllPharmacistEmploymentsForDrugstore(@RequestParam(value = "drugstoreId") String drugstoreId) {
		return new ResponseEntity<>(employmentService.getAllPharmacistsEmploymentsForDrugstore(drugstoreId), HttpStatus.OK);
	}
	
	@GetMapping(path="/employment-for-derm", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<EmploymentDrugstoreDto>> getAllDermatologistEmployments(@RequestParam(value = "dermatologistId") String dermatologistId) {
		return new ResponseEntity<>(employmentService.getAllDermatologistEmployments(dermatologistId), HttpStatus.OK);
	}
	
}
