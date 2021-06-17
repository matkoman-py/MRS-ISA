package pharmacyhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.dto.subscriptions.SubscriptionDetailsDto;
import pharmacyhub.dto.subscriptions.SubscriptionDto;
import pharmacyhub.services.SubscriptionService;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	@PreAuthorize("hasAnyRole('PATIENT')")
	@GetMapping(path="/check", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> checkSubscription(@RequestParam(value = "patientId") String patientId, @RequestParam(value = "drugstoreId") String drugstoreId) throws Exception {
		return new ResponseEntity<>(subscriptionService.checkSubscription(patientId, drugstoreId), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('PATIENT')")
	@PostMapping(path="/subscribe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> subscribe(@RequestBody SubscriptionDto subscriptionDto) throws Exception {
		return new ResponseEntity<>(subscriptionService.subscribe(subscriptionDto.getPatientId(), subscriptionDto.getDrugstoreId()), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('PATIENT')")
	@PostMapping(path="/unsubscribe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> unsubscribe(@RequestBody SubscriptionDto subscriptionDto) throws Exception {
		return new ResponseEntity<>(subscriptionService.unsubscribe(subscriptionDto.getPatientId(), subscriptionDto.getDrugstoreId()), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('PATIENT')")
	@GetMapping(path="/patient/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubscriptionDetailsDto>> getSubsForUser(@PathVariable (value = "patientId") String patientId) throws Exception{
		return new ResponseEntity<>(subscriptionService.readUserSpecificSubs(patientId), HttpStatus.OK);
	}

}
