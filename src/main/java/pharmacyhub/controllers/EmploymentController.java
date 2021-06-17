package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.dto.AddDermatologistToDrugstoreDto;
import pharmacyhub.dto.DermatologistAbsenceRequestDto;
import pharmacyhub.dto.DermatologistAppointmentCreationDto;
import pharmacyhub.dto.DermatologistDto;
import pharmacyhub.dto.EmploymentDrugstoreDto;
import pharmacyhub.dto.EmploymentDto;
import pharmacyhub.dto.HireDermatologistDto;
import pharmacyhub.dto.RemoveDermatologistDto;
import pharmacyhub.services.EmploymentService;

@Controller
@RequestMapping("/employment")
public class EmploymentController {
	
	@Autowired
	private EmploymentService employmentService;
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','SYSTEMADMIN')")
	@PostMapping(path="/dermatologist/hire", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addDermatologistToDrugstore(
			@RequestBody HireDermatologistDto hireDermatologistDto) throws Exception {
		return new ResponseEntity<>(employmentService.hireDermatologist(hireDermatologistDto), HttpStatus.OK);

	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','SYSTEMADMIN')")
	@PostMapping(path="/dermatologist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DermatologistDto> addDermatologistToDrugstore(
			@RequestBody AddDermatologistToDrugstoreDto requestDto) throws Exception {
		return new ResponseEntity<>(employmentService.addDermatologistToDrugstore(requestDto), HttpStatus.OK);
	}
		
	@GetMapping(path="/dermatologists", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistAppointmentCreationDto>> getAllDermatologistsForDrugstore(@RequestParam(value = "drugstoreId") String drugstoreId) {
		return new ResponseEntity<>(employmentService.getAllDermatologistsForDrugstore(drugstoreId), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','SYSTEMADMIN')")
	@GetMapping(path="/dermatologists/notEmployed", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Dermatologist>> getNotEmployedDermatologists(@RequestParam(value = "drugstoreId") String drugstoreId) {
		return new ResponseEntity<>(employmentService.getNotEmployedDermatologists(drugstoreId), HttpStatus.OK);
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
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','SYSTEMADMIN')")
	@DeleteMapping(path="/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> removeDermatologistFromDrugstore(@RequestBody RemoveDermatologistDto info) throws Exception {
		return new ResponseEntity<>(employmentService.removeDermatologistFromDrugstore(info), HttpStatus.OK);

	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','SYSTEMADMIN')")
	@GetMapping(path="/dermatologistRequests", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistAbsenceRequestDto>> getDermatologistAbsenceRequests() throws Exception {
		return new ResponseEntity<>(employmentService.getDermatologistAbsenceRequests(), HttpStatus.OK);

	}
	
}
