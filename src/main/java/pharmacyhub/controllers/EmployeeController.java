package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.domain.users.Employee;
import pharmacyhub.dto.DermatologistDto;
import pharmacyhub.dto.SearchDermatologistDto;
import pharmacyhub.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> findAll() {
		return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> addEmployee(@RequestBody Employee employee) throws Exception {
		return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> update(@RequestBody Employee employee) throws Exception {
		return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);

	}
	
	@PostMapping(path ="/search/dermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistDto>> searchDermatologist(@RequestBody SearchDermatologistDto searchDermatologistDto) {
		return new ResponseEntity<>(employeeService.searchDermatologist(searchDermatologistDto), HttpStatus.OK);
	}
	
}
