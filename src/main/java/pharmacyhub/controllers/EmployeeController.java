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

import pharmacyhub.domain.users.Employee;
import pharmacyhub.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> getGreetings() {
		return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "fill", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> fill() {
		return new ResponseEntity<>(employeeService.setDummyData(), HttpStatus.OK);
	}
	

	@PostMapping(path = "addEmployee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> addEmployee(@RequestBody Employee employee) throws Exception {
		return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> update(@RequestBody Employee employee) throws Exception {
		return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);

	}
}
