package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.domain.users.Supplier;
import pharmacyhub.services.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	SupplierService supplierService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier> add(@RequestBody Supplier supplier) throws Exception {
		return new ResponseEntity<>(supplierService.save(supplier), HttpStatus.OK);
	}
}
