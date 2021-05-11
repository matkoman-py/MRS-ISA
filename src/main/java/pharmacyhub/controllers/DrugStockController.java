package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.dto.AddNewDrugToDrugstoreDto;
import pharmacyhub.dto.DeleteDrugFromStockDto;
import pharmacyhub.dto.DrugStockPriceDto;
import pharmacyhub.services.DrugStockService;

@Controller
@RequestMapping("/drug-stock")
public class DrugStockController {
	
	@Autowired
	private DrugStockService drugStockService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugStockPriceDto>> getDrugStock(@RequestParam(value = "drugstoreId", required=true,  defaultValue = "0") String drugstoreId) throws Exception {
		//trenutno uvek za istu apoteku prikazuje dok se ne odradi logovanje
		return new ResponseEntity<>(drugStockService.returnDrugStockForDrugstore(drugstoreId), HttpStatus.OK);
	}
	
	@GetMapping(path="/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugStockPriceDto>> searchDrugStock(@RequestParam(value = "searchedText", required=false) String searchedText, @RequestParam(value = "drugstoreId") String drugstoreId) throws Exception {
		//trenutno uvek za istu apoteku prikazuje dok se ne odradi logovanje
		System.out.println(searchedText);
		return new ResponseEntity<>(drugStockService.search(searchedText, drugstoreId), HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> addNewDrugToDrugstore(@RequestBody AddNewDrugToDrugstoreDto drugDto) throws Exception {
		return new ResponseEntity<>(drugStockService.addNewDrugToDrugstore(drugDto), HttpStatus.OK);
	}
	
	@DeleteMapping(path="/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteFromStock(@RequestBody DeleteDrugFromStockDto deleteDrugDto) throws Exception {
		return new ResponseEntity<>(drugStockService.deleteFromStock(deleteDrugDto), HttpStatus.OK);
	}
	
}
