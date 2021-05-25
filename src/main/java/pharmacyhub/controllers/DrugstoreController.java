package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.dto.DrugstoreAverageRatingDto;
import pharmacyhub.dto.ereceipt.ReceiptSearchResultsDto;
import pharmacyhub.dto.search.DrugstoreSearchDto;
import pharmacyhub.dto.search.EReceiptSearchDto;
import pharmacyhub.services.DrugstoreService;

@Controller
@RequestMapping("/drugstores")
public class DrugstoreController {

	@Autowired
	private DrugstoreService drugstoreService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drugstore> add(@RequestBody Drugstore drugstore) throws Exception {
		return new ResponseEntity<>(drugstoreService.save(drugstore), HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drugstore> update(@RequestBody Drugstore drugstore) throws Exception {
		return new ResponseEntity<>(drugstoreService.update(drugstore), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> update(@PathVariable String id) {
		try {
			drugstoreService.delete(id);
			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}

	@PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drugstore>> search(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size, 
			@RequestBody DrugstoreSearchDto drugstoreSearchDto) throws Exception {
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(drugstoreService.returnDrugStores(drugstoreSearchDto, pageable), HttpStatus.OK);
	}
	
	@PostMapping(path = "/search-receipt", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReceiptSearchResultsDto> searchReceipt(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size, 
			@RequestBody EReceiptSearchDto eReceiptSearchDto) throws Exception {
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(drugstoreService.eReceiptSearch(eReceiptSearchDto, pageable), HttpStatus.OK);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drugstore>> get(
			@RequestParam(value = "drugStoreNameParam", required = false, defaultValue = "0") String drugStoreName)
			throws Exception {

		return new ResponseEntity<>(drugstoreService.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drugstore> getDrugstore(@PathVariable("id") String id) throws Exception {
		return new ResponseEntity<>(drugstoreService.findDrugstore(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "/reserve", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugStock>> getDrugstores(@RequestParam("drugId") String id,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) 
			throws Exception {
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(drugstoreService.findDrugstores(id,pageable), HttpStatus.OK);
	}
	
	@GetMapping(path = "/reserveEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugStock>> getDrugstoresEmployee(@RequestParam("drugId") String drugId,
			@RequestParam("drugstoreId") String drugstoreId)
			//@RequestParam(value = "page", required = false) Integer page,
			//@RequestParam(value = "size", required = false) Integer size) 
			throws Exception {
		//Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(drugstoreService.findDrugstoreEmployee(drugId,drugstoreId/*pageable*/), HttpStatus.OK);
	}
	
	@GetMapping(path="/adminsDrugstore", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drugstore> getAdminsDrugstore(@RequestParam(value = "adminId") String adminId) throws Exception {
		return new ResponseEntity<>(drugstoreService.getAdminsDrugstore(adminId), HttpStatus.OK);
	}
	
	@PutMapping(path = "/update",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> drugstoreUpdate(@RequestBody Drugstore drugstore) throws Exception {
		return new ResponseEntity<>(drugstoreService.drugstoreUpdate(drugstore), HttpStatus.OK);
	}
	
	@GetMapping(path="/averageRate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DrugstoreAverageRatingDto> calculateAverageRate(@RequestParam(value = "drugstoreId") String drugstoreId) throws Exception {
		return new ResponseEntity<>(drugstoreService.calculateAverageRate(drugstoreId), HttpStatus.OK);
	}
}
