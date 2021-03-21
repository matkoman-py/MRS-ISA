package pharmacyhub.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<String>> getGreetings() {
		Collection<String> greetings = new ArrayList<String>();
		greetings.add("Zoki");
		greetings.add("Djole");
		greetings.add("Mateja");
		greetings.add("Crni");
		
		return new ResponseEntity<Collection<String>>(greetings, HttpStatus.OK);
	}
	
}
