package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.dto.drugOrder.DrugOrderDto;
import pharmacyhub.dto.search.DrugOrderSearchDto;
import pharmacyhub.services.DrugOrderService;

@Controller
@RequestMapping("/drug-orders")
public class DrugOrderController {
	
	@Autowired
	private DrugOrderService drugOrderService;
	
	@PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugOrderDto>> search(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestBody DrugOrderSearchDto drugOrderSearchDto){
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(drugOrderService.search(drugOrderSearchDto, pageable), HttpStatus.OK);
	}
}
