package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.domain.Ingredient;
import pharmacyhub.services.IngredientService;

@Controller
@RequestMapping("/ingredients")
public class IngredeintController {

	@Autowired
	private IngredientService ingrediantService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Ingredient>> getIngredaints() {
		return new ResponseEntity<>(ingrediantService.findAll(), HttpStatus.OK);
	}

}
