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

import pharmacyhub.domain.RatingDrug;
import pharmacyhub.domain.RatingDrugstore;
import pharmacyhub.services.RatingDrugService;

@Controller
@RequestMapping("/rating-drug")
public class RatingDrugController {
	
	@Autowired
	private RatingDrugService ratingDrugService;
	
	@GetMapping(path = "/saveRating", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RatingDrug>> saveRating(
			@RequestParam(value = "drugId", required = true) String drugId,
			@RequestParam(value = "patientId", required = true) String patientId,
			@RequestParam(value = "rating", required = true) String rating
			){
		return new ResponseEntity<>(ratingDrugService.saveRating(drugId,patientId,rating), HttpStatus.OK);
	}
}
