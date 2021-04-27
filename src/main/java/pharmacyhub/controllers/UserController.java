package pharmacyhub.controllers;

import java.util.Collection;

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

import pharmacyhub.domain.users.User;
import pharmacyhub.dto.UserRegistrationDto;
import pharmacyhub.services.RegistrationService;
import pharmacyhub.services.UserSerivce;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private UserSerivce userService;
	
	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> add(@RequestBody UserRegistrationDto requestUser) throws Exception {
		return new ResponseEntity<>(registrationService.registerUser(requestUser), HttpStatus.OK);
	}
	
	@GetMapping(path = "/register/activate/{activation-code}")
	public ResponseEntity<String> activate(@PathVariable("activation-code") String activationCode) throws Exception {
		boolean success = registrationService.verifyActivationCodeAndActivateUser(activationCode);
		return new ResponseEntity<>(success ? "Success" : "Failure", HttpStatus.OK);
	}
	
	@GetMapping(path = "/suppliers-and-admins")
	public ResponseEntity<Collection<User>> getSuppliersAndAdmins() throws Exception {
		return new ResponseEntity<>(userService.getSuppliersAndAdmins(), HttpStatus.OK);
	}
	
}
