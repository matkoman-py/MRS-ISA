package pharmacyhub.controllers;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pharmacyhub.domain.users.DrugstoreAdmin;
import pharmacyhub.domain.users.SystemAdmin;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.User;
import pharmacyhub.dto.FirstPasswordChangeDto;
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
	public ResponseEntity<Boolean> activate(@PathVariable("activation-code") String activationCode) throws Exception {
		boolean success = registrationService.verifyActivationCodeAndActivateUser(activationCode);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN')")
	@GetMapping(path = "/drugstoreAdmin")
	public ResponseEntity<DrugstoreAdmin> getDrugstoreAdmin(@RequestParam String drugstoreAdminId) throws Exception {
		return new ResponseEntity<>(userService.getDrugstoreAdmin(drugstoreAdminId), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN')")
	@GetMapping(path = "/drugstoreAdmin/password")
	public ResponseEntity<Boolean> validatePassword(@RequestParam (value = "drugstoreAdminId") String drugstoreAdminId,
			@RequestParam (value = "passwordInput") String passwordInput) throws Exception {
		return new ResponseEntity<>(userService.validatePassword(drugstoreAdminId, passwordInput), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN')")
	@PutMapping(path="/drugstoreAdminUpdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> drugstoreAdminUpdate(@RequestBody DrugstoreAdmin drugstoreAdmin) throws Exception {
		return new ResponseEntity<>(userService.drugstoreAdminUpdate(drugstoreAdmin), HttpStatus.OK);

	}
	
  @PreAuthorize("hasAnyRole('DRUGSTOREADMIN','SYSTEMADMIN','SUPPLIER','PHARMACIST','DERMATOLOGIST')")
	@PutMapping(path = "/firstLoginPasswordChange",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> firstLoginPasswordChange(@RequestBody FirstPasswordChangeDto firstPasswordChangeDto) throws Exception {
		return new ResponseEntity<>(userService.firstLoginPasswordChange(firstPasswordChangeDto), HttpStatus.OK);
	}
	

  @PreAuthorize("hasAnyRole('DRUGSTOREADMIN')")
	@PutMapping(path = "/drugstoreAdmin/updatepassword",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> drugstoreAdminPasswordUpdate(@RequestBody DrugstoreAdmin drugstoreAdmin) throws Exception {
		return new ResponseEntity<>(userService.drugstoreAdminPasswordUpdate(drugstoreAdmin), HttpStatus.OK);
	}
	

	

  @PreAuthorize("hasAnyRole('SYSTEMADMIN')")
	@DeleteMapping(path = "/drugstoreAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deleteDrugstoreAdmin(@RequestParam(value = "id") String drugstoreAdminId) throws Exception {
		return new ResponseEntity<>(userService.deleteDrugstoreAdmin(drugstoreAdminId), HttpStatus.OK);
	}
	
  @PreAuthorize("hasAnyRole('SYSTEMADMIN')")
	@GetMapping(path = "/suppliers-and-admins")
	public ResponseEntity<Collection<User>> getSuppliersAndAdmins() throws Exception {
		return new ResponseEntity<>(userService.getSuppliersAndAdmins(), HttpStatus.OK);
	}
	////////////////////////////
	@PreAuthorize("hasAnyRole('PATIENT')")
	@GetMapping(path = "/user")
	public ResponseEntity<Patient> getUser(@RequestParam String drugstoreAdminId) throws Exception {
		return new ResponseEntity<>(userService.getUser(drugstoreAdminId), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('SYSTEMADMIN')")
	@GetMapping(path = "/systemAdmin")
	public ResponseEntity<SystemAdmin> getSystemAdmin(@RequestParam String systemAdminId) throws Exception {
		return new ResponseEntity<>(userService.getSystemAdmin(systemAdminId), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('SYSTEMADMIN')")
	@PutMapping(path="/systemAdminUpdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> systemAdminUpdate(@RequestBody SystemAdmin systemAdmin) throws Exception {
		return new ResponseEntity<>(userService.systemAdminUpdate(systemAdmin), HttpStatus.OK);

	}
	
	@PreAuthorize("hasAnyRole('SYSTEMADMIN')")
	@GetMapping(path = "/systemAdmin/password")
	public ResponseEntity<Boolean> validatePasswordsystemAdmin(@RequestParam (value = "systemAdminId") String systemAdminId,
			@RequestParam (value = "passwordInput") String passwordInput) throws Exception {
		return new ResponseEntity<>(userService.validatePasswordsystemAdmin(systemAdminId, passwordInput), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('SYSTEMADMIN')")
	@PutMapping(path = "/systemAdmin/updatepassword",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> systemAdminPasswordUpdate(@RequestBody SystemAdmin systemAdmin) throws Exception {
		return new ResponseEntity<>(userService.systemAdminPasswordUpdate(systemAdmin), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('PATIENT')")
	@GetMapping(path = "/user/password")
	public ResponseEntity<Boolean> validateUserPassword(@RequestParam (value = "drugstoreAdminId") String drugstoreAdminId,
			@RequestParam (value = "passwordInput") String passwordInput) throws Exception {
		return new ResponseEntity<>(userService.validatePasswordUser(drugstoreAdminId, passwordInput), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('PATIENT')")
	@PutMapping(path = "/user/updatepassword",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> userPasswordUpdate(@RequestBody DrugstoreAdmin drugstoreAdmin) throws Exception {
		return new ResponseEntity<>(userService.userPasswordUpdate(drugstoreAdmin), HttpStatus.OK);
	}
}
