package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.domain.Ingrediant;
import pharmacyhub.services.IngrediantService;

@Controller
@RequestMapping("/ingrediants")
public class IngredaintController {

	@Autowired
	private IngrediantService ingrediantService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Ingrediant>> getIngredaints() {
		return new ResponseEntity<>(ingrediantService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path ="/seed", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Ingrediant>> seedIngredaints() {
		return new ResponseEntity<>(ingrediantService.seedIngrediants(), HttpStatus.OK);
	}
	
	
}
