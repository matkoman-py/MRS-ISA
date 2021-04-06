package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Employee;
import pharmacyhub.services.DermatologistEmploymentService;


@Controller
@RequestMapping("/dermatologist-employment")
public class DermatologistEmploymentController {
	
	@Autowired
	private DermatologistEmploymentService dermatologistEmploymentService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Dermatologist>> getAllDermatologistsForDrugstore(@RequestParam(value = "drugstoreId") String drugstoreId) {
		return new ResponseEntity<>(dermatologistEmploymentService.getAllDermatologistsForDrugstore(drugstoreId), HttpStatus.OK);
	}
}
