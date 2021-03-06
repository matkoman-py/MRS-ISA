package pharmacyhub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.domain.DrugPrice;
import pharmacyhub.dto.CreateNewPriceForDrugDto;
import pharmacyhub.services.DrugPriceService;

@Controller
@RequestMapping("/drug-price")
public class DrugPriceController {
	
	@Autowired
	private DrugPriceService drugPriceService;
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DrugPrice> add(@RequestBody CreateNewPriceForDrugDto drugPrice) throws Exception {
		return new ResponseEntity<>(drugPriceService.save(drugPrice), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN')")
	@PostMapping(path="/promotion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DrugPrice> createPromotion(@RequestBody CreateNewPriceForDrugDto drugPromotion) throws Exception {
		return new ResponseEntity<>(drugPriceService.savePromotion(drugPromotion), HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getPrice(@RequestParam(value = "drugId", required=true,  defaultValue = "0") String drugId,
											@RequestParam(value = "drugstoreId", required=true,  defaultValue = "0") String drugstoreId) throws Exception {
		return new ResponseEntity<>(drugPriceService.getPrice(drugId,drugstoreId), HttpStatus.OK);
	}
}
