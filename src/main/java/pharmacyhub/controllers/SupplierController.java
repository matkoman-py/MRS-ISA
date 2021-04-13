package pharmacyhub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.services.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	SupplierService supplierService;
	
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> update(@PathVariable String id) {
		try {
			supplierService.delete(id);
			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}
}
