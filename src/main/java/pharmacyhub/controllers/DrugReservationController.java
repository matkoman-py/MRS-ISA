package pharmacyhub.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.domain.DrugReservation;
import pharmacyhub.dto.DrugReservationDto;
import pharmacyhub.dto.DrugReservationEmployeeDto;
import pharmacyhub.dto.search.DrugReservationCancelDto;
import pharmacyhub.exception.DrugOutOfStockException;
import pharmacyhub.services.DrugReservationService;

@Controller
@RequestMapping("/drugReservation")
public class DrugReservationController {
	
	@Autowired
	private DrugReservationService drugReservationService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugReservation>> getDrugStock() throws Exception {
		return new ResponseEntity<>(drugReservationService.findAll(), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('PATIENT')")
	@PostMapping(path = "/saveReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveReservation(@RequestBody DrugReservationDto drugreservationDto) throws Exception {
		return new ResponseEntity<>(drugReservationService.saveReservation(drugreservationDto), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('DERMATOLOGIST',  'PHARMACIST')")
	@PostMapping(path = "/saveReservationEmployee",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveReservation(@RequestBody DrugReservationEmployeeDto drugreservationDto) throws Exception {
		return new ResponseEntity<>(drugReservationService.saveReservationEmployee(drugreservationDto), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('PATIENT')")
	@PostMapping(path = "/saveMultipleReservations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveMultipleReservations(@RequestBody List<DrugReservationDto> drugreservationDtos) throws Exception {
		return new ResponseEntity<>(drugReservationService.saveMultipleReservations(drugreservationDtos), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('PATIENT')")
	@GetMapping(path = "/getPatientReservationsLength",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getPatientReservationsLength(@RequestParam(value = "patientId", required = false, defaultValue = "0") String patientId) throws Exception {
		return new ResponseEntity<>(drugReservationService.getPatientReservations(patientId), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('PATIENT')")
	@GetMapping(path = "/getPatientReservations",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugReservation>> getPatientReservations(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "patientId", required = false, defaultValue = "0") String patientId) throws Exception {
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(drugReservationService.getPatientReservations(patientId, pageable), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('PATIENT')")
	@PutMapping(path = "/cancelReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugReservation>> cancelReservation(@RequestBody DrugReservationCancelDto drugreservationcancelDto) throws Exception {

		return new ResponseEntity<>(drugReservationService.cancelReservation(drugreservationcancelDto), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('PHARMACIST')")
	@GetMapping(path = "/getPatientReservationsInDrugstore",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugReservation>> getPatientReservationsInDrugstore(@RequestParam(value = "patientEmail", required = false, defaultValue = "0") String patientEmail,
			@RequestParam(value = "pharmacistId", required = false, defaultValue = "0") String pharmacistId) throws Exception {
		return new ResponseEntity<>(drugReservationService.getPatientReservationsInDrugstore(patientEmail, pharmacistId), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('PHARMACIST')")
	@GetMapping(path = "/issueReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> issueReservation(@RequestParam(value = "reservationId", required = false, defaultValue = "0") String reservationId,
			@RequestParam(value = "confirmationCode", required = false, defaultValue = "0") String confirmationCode) throws Exception {
		return new ResponseEntity<>(drugReservationService.issueReservation(reservationId,confirmationCode), HttpStatus.OK);
	}
}
