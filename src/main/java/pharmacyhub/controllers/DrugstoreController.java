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

import pharmacyhub.domain.Drugstore;
import pharmacyhub.services.DrugstoreService;

@Controller
@RequestMapping("/drugstores")
public class DrugstoreController {
	
	@Autowired
	private DrugstoreService drugstoreService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drugstore> add(@RequestBody Drugstore drugstore) throws Exception {
		return new ResponseEntity<>(drugstoreService.save(drugstore), HttpStatus.OK);
	}
}
