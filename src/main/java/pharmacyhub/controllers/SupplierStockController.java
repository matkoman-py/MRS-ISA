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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.dto.search.SupplierStockSearchDto;
import pharmacyhub.dto.supplier.EditSupplierStockDto;
import pharmacyhub.dto.supplier.SupplierStockDto;
import pharmacyhub.services.SupplierStockService;

@Controller
@RequestMapping("/supplier-stocks")
public class SupplierStockController {

	@Autowired
	private SupplierStockService supplierStockService;

	@PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<SupplierStockDto>> search(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestBody SupplierStockSearchDto searchDto) {
		
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(supplierStockService.search(searchDto, pageable), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SupplierStockDto> add(@RequestBody SupplierStockDto supplierStockDto) throws Exception {
		return new ResponseEntity<>(supplierStockService.add(supplierStockDto), HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SupplierStockDto> update(@RequestBody EditSupplierStockDto editSupplierStockDto)
			throws Exception {
		return new ResponseEntity<>(supplierStockService.update(editSupplierStockDto), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Boolean> deleteDrug(@PathVariable("id") String id) throws Exception {
		return new ResponseEntity<>(supplierStockService.delete(id), HttpStatus.OK);
	}

}
