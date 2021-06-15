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

import pharmacyhub.domain.RatingDrugstore;
import pharmacyhub.domain.RatingPharmacist;
import pharmacyhub.services.RatingPharmacistService;

@Controller
@RequestMapping("/rating-pharmacist")
public class RatingPharmacistController {

	@Autowired
	private RatingPharmacistService ratingPharmacistService;
	@PreAuthorize("hasAnyRole('PATIENT')")
	@GetMapping(path = "/saveRating", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RatingPharmacist>> saveRating(
			@RequestParam(value = "pharmacistId", required = true) String pharmacistId,
			@RequestParam(value = "patientId", required = true) String patientId,
			@RequestParam(value = "rating", required = true) String rating
			){
		return new ResponseEntity<>(ratingPharmacistService.saveRating(pharmacistId,patientId,rating), HttpStatus.OK);
	}
}
