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

import pharmacyhub.domain.RatingDermatologist;
import pharmacyhub.domain.RatingPharmacist;
import pharmacyhub.services.RatingDermatologistService;

@Controller
@RequestMapping("/rating-dermatologist")
public class RatingDermatologistController {
	@Autowired
	private RatingDermatologistService ratingDermatologistService;
	
	@GetMapping(path = "/saveRating", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RatingDermatologist>> saveRating(
			@RequestParam(value = "dermatologistId", required = true) String dermatologistId,
			@RequestParam(value = "patientId", required = true) String patientId,
			@RequestParam(value = "rating", required = true) String rating
			){
		return new ResponseEntity<>(ratingDermatologistService.saveRating(dermatologistId,patientId,rating), HttpStatus.OK);
	}
}
