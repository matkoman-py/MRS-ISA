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
import org.springframework.web.bind.annotation.RequestParam;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Location;
import pharmacyhub.repositories.LocationRepository;
import pharmacyhub.services.DrugstoreService;

@Controller
@RequestMapping("/drugstores")
public class DrugstoreController {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private DrugstoreService drugstoreService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drugstore> add(@RequestBody Drugstore drugstore) throws Exception {
		return new ResponseEntity<>(drugstoreService.save(drugstore), HttpStatus.OK);
	}
	
	@GetMapping(path ="/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drugstore>> test(@RequestParam(value = "drugStoreNameParam", required=false,  defaultValue = "0") String drugStoreName,
													  @RequestParam(value = "drugStoreCityParam", required=false,  defaultValue = "0") String drugStoreCity,
													  @RequestParam(value = "drugStoreCountryParam", required=false,  defaultValue = "0") String drugStoreCountry
												) throws Exception {
		
		return new ResponseEntity<>(drugstoreService.returnDrugStores(drugStoreName,drugStoreCity,drugStoreCountry), HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drugstore>> get(@RequestParam(value = "drugStoreNameParam", required=false,  defaultValue = "0") String drugStoreName
												) throws Exception {
		
		return new ResponseEntity<>(drugstoreService.findAll(), HttpStatus.OK);
	}
}