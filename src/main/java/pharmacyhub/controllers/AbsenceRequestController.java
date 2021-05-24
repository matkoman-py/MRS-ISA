package pharmacyhub.controllers;

import java.text.ParseException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.dto.AbsenceRequestDto;
import pharmacyhub.dto.PharmacistRequestRejectionDto;
import pharmacyhub.services.AbsenceRequestService;


@Controller
@RequestMapping("/absence-request")
public class AbsenceRequestController {
	
	@Autowired
	private AbsenceRequestService absenceRequestService;
	
	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody AbsenceRequestDto createAbsenceRequestDto) throws ParseException{
		return new ResponseEntity<>(absenceRequestService.createNewRequest(createAbsenceRequestDto), HttpStatus.OK);
	}
	
	@PostMapping(path = "/approve/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> approveRequestForPharmacist(@PathVariable("requestId") String requestId) throws ParseException, MessagingException{
		return new ResponseEntity<>(absenceRequestService.approveRequest(requestId), HttpStatus.OK);
	}
	
	@PostMapping(path = "/reject", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> rejectRequestForPharmacist(@RequestBody PharmacistRequestRejectionDto pharmacistRequestRejectionDto) throws ParseException, MessagingException{
		return new ResponseEntity<>(absenceRequestService.rejectRequest(pharmacistRequestRejectionDto), HttpStatus.OK);
	}
}
