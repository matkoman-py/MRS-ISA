package pharmacyhub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.dto.UserRegistrationDto;
import pharmacyhub.services.RegistrationService;

@Controller
@RequestMapping("/register")
public class UserController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> add(@RequestBody UserRegistrationDto requestUser) throws Exception {
		return new ResponseEntity<>(registrationService.registerUser(requestUser), HttpStatus.OK);
	}
	
	@GetMapping(path = "activate/{activation-code}")
	public ResponseEntity<String> activate(@PathVariable("activation-code") String activationCode) throws Exception {
		boolean success = registrationService.verifyActivationCodeAndActivateUser(activationCode);
		return new ResponseEntity<>(success ? "Success" : "Failue", HttpStatus.OK);
	}
}