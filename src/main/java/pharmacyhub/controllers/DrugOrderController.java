package pharmacyhub.controllers;

import java.util.Collection;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.dto.CreateOrderDto;
import pharmacyhub.dto.DrugOrderOverviewDto;
import pharmacyhub.dto.drugOrder.DrugOrderDto;
import pharmacyhub.dto.search.DrugOrderSearchDto;
import pharmacyhub.services.DrugOrderService;

@Controller
@RequestMapping("/drug-orders")
public class DrugOrderController {
	
	@Autowired
	private DrugOrderService drugOrderService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugOrderOverviewDto>> getOrdersForDrugstore(@RequestParam String drugstoreId, @RequestParam String filter) throws Exception {
		return new ResponseEntity<>(drugOrderService.getOrdersForDrugstore(drugstoreId, filter), HttpStatus.OK);
	}
	
	@PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DrugOrderDto>> search(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestBody DrugOrderSearchDto drugOrderSearchDto){
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(drugOrderService.search(drugOrderSearchDto, pageable), HttpStatus.OK);
	}
	
	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody CreateOrderDto createOrderDto){
		return new ResponseEntity<>(drugOrderService.createNewOrder(createOrderDto), HttpStatus.OK);
	}
	
	@PostMapping(path = "/expired/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> orderExpired(@PathVariable("id") String orderId){
		return new ResponseEntity<>(drugOrderService.orderExpired(orderId), HttpStatus.OK);
	}
	
	@PostMapping(path = "/accepted/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> orderAccepted(@PathVariable("id") String offerId) throws MessagingException{
		return new ResponseEntity<>(drugOrderService.orderAccepted(offerId), HttpStatus.OK);
	}
	
	@PostMapping(path = "/declined/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> orderDeclined(@PathVariable("id") String orderId) throws MessagingException{
		return new ResponseEntity<>(drugOrderService.orderDeclined(orderId), HttpStatus.OK);
	}
}
