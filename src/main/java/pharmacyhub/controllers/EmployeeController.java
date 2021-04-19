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
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.domain.users.Employee;
import pharmacyhub.dto.DermatologistDto;
import pharmacyhub.dto.DermatologistOverviewDto;
import pharmacyhub.dto.PharmacistOverviewDto;
import pharmacyhub.dto.SearchDermatologistDto;
import pharmacyhub.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> getAllEmployeesOfDrugstore(@RequestParam(value = "drugstoreId") String drugstoreId) {
		return new ResponseEntity<>(employeeService.getAllEmployeesOfDrugstore(drugstoreId), HttpStatus.OK);
	}
	
	@GetMapping(path="/pharmacists", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PharmacistOverviewDto>> getAllPharmacists() {
		return new ResponseEntity<>(employeeService.getAllPharmacists(), HttpStatus.OK);
	}
	
	@GetMapping(path="/pharmacists/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PharmacistOverviewDto>> getPharmacistBySearch(@RequestParam(value = "name") String name,
			@RequestParam(value = "surname") String surname, @RequestParam(value = "minRate") double minRate,
			@RequestParam(value = "maxRate") double maxRate, @RequestParam(value = "drugstore") String drugstore) {
		return new ResponseEntity<>(employeeService.getPharmacistBySearch(name, surname, minRate, maxRate, drugstore), HttpStatus.OK);
	}
	
	@GetMapping(path="/dermatologists", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistOverviewDto>> getAlDermatologists() {
		return new ResponseEntity<>(employeeService.getAlDermatologists(), HttpStatus.OK);
	}
	
	@GetMapping(path="/dermatologists/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistOverviewDto>> getDermatologistBySearch(@RequestParam(value = "name") String name,
			@RequestParam(value = "surname") String surname, @RequestParam(value = "minRate") double minRate,
			@RequestParam(value = "maxRate") double maxRate, @RequestParam(value = "drugstore") String drugstore) {
		return new ResponseEntity<>(employeeService.getDermatologistBySearch(name, surname, minRate, maxRate, drugstore), HttpStatus.OK);
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
	

	@GetMapping(path ="/id",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> findOne(@RequestParam (value = "employeeId", required=false,  defaultValue = "0") String employeeId) throws Exception {
		return new ResponseEntity<>(employeeService.findOne(employeeId), HttpStatus.OK);
	}

}
