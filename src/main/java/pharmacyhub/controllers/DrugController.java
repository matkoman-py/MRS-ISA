package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import pharmacyhub.domain.Drug;
import pharmacyhub.dto.CreateDrugOrderDto;
import pharmacyhub.dto.DrugDto;
import pharmacyhub.dto.DrugInDrugstoreDto;
import pharmacyhub.dto.search.DrugSearchDto;
import pharmacyhub.services.DrugService;

@Controller
@RequestMapping("/drugs")
public class DrugController {

	@Autowired
	private DrugService drugService;

	@PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drug>> search(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestBody DrugSearchDto searchDto) {
		Sort sortSetting = Sort.by("name");
		if(searchDto.getSortByField() != null && !searchDto.getSortByField().isBlank()) {
			sortSetting = (searchDto.getAscending()) ? Sort.by(searchDto.getSortByField()).ascending() : Sort.by(searchDto.getSortByField()).descending();
		}
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size, sortSetting);
		return new ResponseEntity<>(drugService.returnDrugs(searchDto, pageable), HttpStatus.OK);
	}
	
	@PostMapping(path = "/searchLength", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> search(@RequestBody DrugSearchDto searchDto) {
		return new ResponseEntity<>(drugService.returnDrugsLength(searchDto), HttpStatus.OK);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drug>> getDrugs(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(drugService.findAll(pageable), HttpStatus.OK);
	}

	@GetMapping(path = "/notOnStock", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drug>> getDrugsNotOnStock(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "drugstoreId", required = true) String drugstoreId){
		return new ResponseEntity<>(drugService.getDrugsNotOnStock(drugstoreId), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('SYSTEMADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drug> add(@RequestBody DrugDto drugDto) throws Exception {
		return new ResponseEntity<>(drugService.save(drugDto), HttpStatus.OK);
	}

	@GetMapping(path = "/in-drugstore/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugInDrugstoreDto>> getDrugsInDrugstore(@PathVariable("id") String drugstoreId,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(drugService.getDrugsInDrugstore(drugstoreId, pageable), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('SYSTEMADMIN')")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drug> update(@RequestBody DrugDto drugDto) throws Exception {
		return new ResponseEntity<>(drugService.update(drugDto), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('SYSTEMADMIN')")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Boolean> deleteDrug(@PathVariable("id") String id) throws Exception {
		return new ResponseEntity<>(drugService.delete(id), HttpStatus.OK);
	}
	
	@GetMapping(path="/substitutions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drug>> getSubstituteDrugs(@RequestParam(value = "drugId", required = false, defaultValue = "0") String drugId) {
		
		return new ResponseEntity<>(drugService.findAllSubstitutes(drugId), HttpStatus.OK);
	}
	
	@GetMapping(path="/substitutionsDrugstore", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drug>> getSubstituteDrugs(@RequestParam(value = "drugId", required = false, defaultValue = "0") String drugId,
			@RequestParam(value = "drugstoreId", required = false, defaultValue = "0") String drugstoreId,
			@RequestParam(value = "patientId", required = false, defaultValue = "0") String patientId) {
		
		return new ResponseEntity<>(drugService.findAllSubstitutesDrugstore(drugId,drugstoreId,patientId), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN')")
	@GetMapping(path = "/createOrderView", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<CreateDrugOrderDto>> getDrugsForCreateOrderView(
			@RequestParam(value = "drugstoreId", required = true) String drugstoreId,
			@RequestParam(value = "drugsToBeShown", required = true) String drugsToBeShown) {
		return new ResponseEntity<>(drugService.getDrugsForCreateOrderView(drugstoreId, drugsToBeShown), HttpStatus.OK);
	}
}
