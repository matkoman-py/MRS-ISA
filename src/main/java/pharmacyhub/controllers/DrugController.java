package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.domain.Drug;
import pharmacyhub.services.DrugService;

@Controller
@RequestMapping("/drugs")
public class DrugController {

	@Autowired
	private DrugService drugService;

	@GetMapping(path = "/seed", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drug>> seedDrugs() {
		return new ResponseEntity<>(drugService.findAll(), HttpStatus.OK);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drug>> getDrugs() {
		return new ResponseEntity<>(drugService.findAll(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drug> add(@RequestBody Drug drug) throws Exception {
		return new ResponseEntity<>(drugService.save(drug), HttpStatus.OK);
	}
}
