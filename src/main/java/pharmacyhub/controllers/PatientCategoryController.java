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

import pharmacyhub.domain.LoyaltyConfiguration;
import pharmacyhub.domain.PatientCategory;
import pharmacyhub.dto.LoyaltyConfigurationDto;
import pharmacyhub.dto.PatientCategoryDto;
import pharmacyhub.services.PatientCategoryService;

@Controller
@RequestMapping("/patient-categories")
public class PatientCategoryController {

	@Autowired
	private PatientCategoryService patientCategoryService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PatientCategory>> getAll() {
		return new ResponseEntity<>(patientCategoryService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(path="/configuration", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoyaltyConfiguration> getLoyaltyConfiguration() {
		return new ResponseEntity<>(patientCategoryService.getLoyaltyConfiguration(), HttpStatus.OK);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PatientCategory> update(@RequestBody PatientCategoryDto patientCategory) throws Exception {
		return new ResponseEntity<>(patientCategoryService.updatePatientCategory(patientCategory), HttpStatus.OK);
	}
	
	@PutMapping(path="/configuration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoyaltyConfiguration> update(@RequestBody LoyaltyConfigurationDto loyaltyConfiguration) throws Exception {
		return new ResponseEntity<>(patientCategoryService.updateLoyaltyConfiguration(loyaltyConfiguration), HttpStatus.OK);
	}
}
