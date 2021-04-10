package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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

import pharmacyhub.domain.Drug;
import pharmacyhub.services.DrugService;

@Controller
@RequestMapping("/drugs")
public class DrugController {

	@Autowired
	private DrugService drugService;

	
	@GetMapping(path ="/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drug>> search(@RequestParam(value = "drugNameParam", required=false,  defaultValue = "0") String drugName,
												 @RequestParam(value = "drugTypeParam", required=false,  defaultValue = "0") String drugType,
												 @RequestParam(value = "drugFormParam", required=false,  defaultValue = "0") String drugForm,
												 @RequestParam(value = "drugManufacturerParam", required=false,  defaultValue = "0") String drugManufacturer,
												 @RequestParam(value = "drugReceiptParam", required=false,  defaultValue = "0") String drugReceipt) {
		
		return new ResponseEntity<>(drugService.returnDrugs(drugName,drugType,drugForm,drugManufacturer,drugReceipt), HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drug>> getDrugs() {
		return new ResponseEntity<>(drugService.findAll(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drug> add(@RequestBody Drug drug) throws Exception {
		return new ResponseEntity<>(drugService.save(drug), HttpStatus.OK);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drug> update(@RequestBody Drug drug) throws Exception {
		return new ResponseEntity<>(drugService.update(drug), HttpStatus.OK);
	}
	
	@DeleteMapping(path ="/{id}")
	public ResponseEntity<Boolean> deleteDrug(@PathVariable("id") String id) throws Exception {
		return new ResponseEntity<>(drugService.delete(id), HttpStatus.OK);
	}
}
