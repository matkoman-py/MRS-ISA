package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.domain.DrugType;
import pharmacyhub.services.DrugTypeService;

@Controller
@RequestMapping("/drug-types")
public class DrugTypeController {

	@Autowired
	private DrugTypeService drugTypeService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugType>> getDrugTypes() {
		return new ResponseEntity<>(drugTypeService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path ="/seed", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugType>> seed() {
		return new ResponseEntity<>(drugTypeService.seed(), HttpStatus.OK);
	}
	
}
