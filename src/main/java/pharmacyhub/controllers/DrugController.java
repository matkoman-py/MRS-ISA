package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.domain.Drug;
import pharmacyhub.services.DrugService;

@Controller
@RequestMapping("/drugs")
public class DrugController {
	
	@Autowired
	private DrugService drugService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drug>> getGreetings() {
		return new ResponseEntity<>(drugService.readAll(), HttpStatus.OK);
	}
}
