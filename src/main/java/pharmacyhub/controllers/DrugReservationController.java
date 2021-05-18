package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugReservation;
import pharmacyhub.dto.DrugReservationDto;
import pharmacyhub.dto.search.DrugReservationCancelDto;
import pharmacyhub.dto.search.DrugstoreSearchDto;
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
	
	@PostMapping(path = "/saveReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveReservation(@RequestBody DrugReservationDto drugreservationDto) throws Exception {
		return new ResponseEntity<>(drugReservationService.saveReservation(drugreservationDto), HttpStatus.OK);
	}

	@GetMapping(path = "/getPatientReservations",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugReservation>> getPatientReservations(@RequestParam(value = "patientId", required = false, defaultValue = "0") String patientId) throws Exception {
		return new ResponseEntity<>(drugReservationService.getPatientReservations(patientId), HttpStatus.OK);
	}
	
	@PutMapping(path = "/cancelReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugReservation>> cancelReservation(@RequestBody DrugReservationCancelDto drugreservationcancelDto) throws Exception {

		return new ResponseEntity<>(drugReservationService.cancelReservation(drugreservationcancelDto), HttpStatus.OK);
	}
}
