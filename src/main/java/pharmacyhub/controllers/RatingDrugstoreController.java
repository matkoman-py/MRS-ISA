package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.RatingDrugstore;
import pharmacyhub.services.RatingDrugstoreService;

@Controller
@RequestMapping("/rating-drugsore")
public class RatingDrugstoreController {
	
	@Autowired
	private RatingDrugstoreService ratingDrugstoreService;
	@PreAuthorize("hasAnyRole('PATIENT')")
	@GetMapping(path = "/saveRating", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RatingDrugstore>> saveRating(
			@RequestParam(value = "drugstoreId", required = true) String drugstoreId,
			@RequestParam(value = "patientId", required = true) String patientId,
			@RequestParam(value = "rating", required = true) String rating
			){
		return new ResponseEntity<>(ratingDrugstoreService.saveRating(drugstoreId,patientId,rating), HttpStatus.OK);
	}
}
