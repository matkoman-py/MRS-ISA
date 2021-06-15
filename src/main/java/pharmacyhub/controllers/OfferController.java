package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import pharmacyhub.domain.Offer;
import pharmacyhub.dto.offer.OfferDto;
import pharmacyhub.dto.search.OfferSearchDto;
import pharmacyhub.services.OfferService;

@Controller
@RequestMapping("/offers")
public class OfferController {
	@Autowired
	private OfferService offerService;
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','SYSTEMADMIN')")
	@PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<OfferDto>> search(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestBody OfferSearchDto offerSearchDto){
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(offerService.search(offerSearchDto, pageable), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('SUPPLIER')")
	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OfferDto> makeOffer(@RequestBody OfferDto offerDto) throws Exception{
		return new ResponseEntity<>(offerService.makeOffer(offerDto), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN','SYSTEMADMIN','SUPPLIER')")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Offer>> getOffersForOrder(@RequestParam String orderId) throws Exception {
		return new ResponseEntity<>(offerService.getOffersForOrder(orderId), HttpStatus.OK);
	}
}
