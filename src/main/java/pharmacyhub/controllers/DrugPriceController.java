package pharmacyhub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.domain.DrugPrice;
import pharmacyhub.dto.CreateNewPriceForDrugDto;
import pharmacyhub.services.DrugPriceService;

@Controller
@RequestMapping("/drug-price")
public class DrugPriceController {
	
	@Autowired
	private DrugPriceService drugPriceService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DrugPrice> add(@RequestBody CreateNewPriceForDrugDto drugPrice) throws Exception {
		return new ResponseEntity<>(drugPriceService.save(drugPrice), HttpStatus.OK);
	}
	
	@PostMapping(path="/promotion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DrugPrice> createPromotion(@RequestBody CreateNewPriceForDrugDto drugPromotion) throws Exception {
		return new ResponseEntity<>(drugPriceService.savePromotion(drugPromotion), HttpStatus.OK);
	}
	
}
