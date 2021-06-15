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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.Employee;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.dto.DermatologistDto;
import pharmacyhub.dto.DermatologistOverviewDto;
import pharmacyhub.dto.EmployeeOverviewDto;
import pharmacyhub.dto.PharmacistAbsenceRequestDto;
import pharmacyhub.dto.PharmacistOverviewDto;
import pharmacyhub.dto.SearchDermatologistDto;
import pharmacyhub.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN')")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<EmployeeOverviewDto>> getAllEmployeesOfDrugstore(@RequestParam(value = "drugstoreAdminId") String drugstoreAdminId) {
		return new ResponseEntity<>(employeeService.getAllEmployeesOfDrugstore(drugstoreAdminId), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN')")
	@GetMapping(path="/pharmacistRequests", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PharmacistAbsenceRequestDto>> getPharmacistRequests(@RequestParam(value = "drugstoreId") String drugstoreId) {
		return new ResponseEntity<>(employeeService.getPharmacistRequests(drugstoreId), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','PATIENT')")
	@GetMapping(path="/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<EmployeeOverviewDto>> getAllEmployeesBySearch(@RequestParam(value = "drugstoreAdminId") String drugstoreAdminId,
			@RequestParam(value = "searchText") String searchText, @RequestParam(value = "minRate") double minRate,
			@RequestParam(value = "maxRate") double maxRate, @RequestParam(value = "type") String employeeType) {
		return new ResponseEntity<>(employeeService.getAllEmployeesOfDrugstoreBySearch(drugstoreAdminId, searchText, minRate, maxRate, employeeType), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','PATIENT')")
	@GetMapping(path="/pharmacists", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PharmacistOverviewDto>> getAllPharmacists() {
		return new ResponseEntity<>(employeeService.getAllPharmacists(), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','PATIENT')")
	@GetMapping(path="/pharmacists/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PharmacistOverviewDto>> getPharmacistBySearch(@RequestParam(value = "name") String name,
			@RequestParam(value = "surname") String surname, @RequestParam(value = "minRate") double minRate,
			@RequestParam(value = "maxRate") double maxRate, @RequestParam(value = "drugstore") String drugstore) {
		return new ResponseEntity<>(employeeService.getPharmacistBySearch(name, surname, minRate, maxRate, drugstore), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','PATIENT')")
	@GetMapping(path="/dermatologists", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistOverviewDto>> getAlDermatologists() {
		return new ResponseEntity<>(employeeService.getAllDermatologists(), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','PATIENT')")
	@GetMapping(path="/dermatologists/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistOverviewDto>> getDermatologistBySearch(@RequestParam(value = "name") String name,
			@RequestParam(value = "surname") String surname, @RequestParam(value = "minRate") double minRate,
			@RequestParam(value = "maxRate") double maxRate, @RequestParam(value = "drugstore") String drugstore) {
		return new ResponseEntity<>(employeeService.getDermatologistBySearch(name, surname, minRate, maxRate, drugstore), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','SYSTEMADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> addEmployee(@RequestBody Employee employee) throws Exception {
		return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK); // koristi se samo za dermatologa sad, treba preimenovati i srediti
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','SYSTEMADMIN')")
	@PostMapping(path="/pharmacist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Pharmacist>> addEmployee(@RequestBody Pharmacist pharmacist) throws Exception {
		return new ResponseEntity<>(employeeService.addPharmacist(pharmacist), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DERMATOLOGIST','PHARMACIST')")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> update(@RequestBody Employee employee) throws Exception {
		return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','PATIENT')")
	@PostMapping(path ="/search/dermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DermatologistDto>> searchDermatologist(@RequestBody SearchDermatologistDto searchDermatologistDto) {
		return new ResponseEntity<>(employeeService.searchDermatologist(searchDermatologistDto), HttpStatus.OK);
	}
	

	@GetMapping(path ="/id",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> findOne(@RequestParam (value = "employeeId", required=false,  defaultValue = "0") String employeeId) throws Exception {
		return new ResponseEntity<>(employeeService.findOne(employeeId), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','SYSTEMADMIN')")
	@GetMapping(path ="/drugstoreForId",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drugstore> findDrugstoreByEmployeeId(@RequestParam (value = "drugstoreAdminId") String drugstoreAdminId) throws Exception {
		return new ResponseEntity<>(employeeService.findDrugstoreByDrugstoreAdminId(drugstoreAdminId), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','SYSTEMADMIN')")
	@DeleteMapping(path="/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletePharmacist(@RequestParam (value = "pharmacistEmail") String pharmacistEmail) throws Exception {
		return new ResponseEntity<>(employeeService.deletePharmacist(pharmacistEmail), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DERMATOLOGIST','PHARMACIST')")
	@GetMapping(path ="/password",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> passwordValid(@RequestParam (value = "employeeId") String employeeId,
			@RequestParam (value = "passwordInput") String passwordInput) throws Exception {
		return new ResponseEntity<>(employeeService.passwordValid(employeeId, passwordInput), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DERMATOLOGIST','PHARMACIST')")
	@PutMapping(path = "/updatepassword",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> updatePassword(@RequestBody Employee employee) throws Exception {
		return new ResponseEntity<>(employeeService.updatePassword(employee), HttpStatus.OK);
	}
}
