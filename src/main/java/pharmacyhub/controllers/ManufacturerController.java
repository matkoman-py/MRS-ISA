package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.domain.Manufacturer;
import pharmacyhub.services.ManufacturerService;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Manufacturer>> getManufacturers() {
		return new ResponseEntity<>(manufacturerService.readAll(), HttpStatus.OK);
	}
	
}
