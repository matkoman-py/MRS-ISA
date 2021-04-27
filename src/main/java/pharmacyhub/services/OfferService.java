package pharmacyhub.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Offer;
import pharmacyhub.dto.offer.OfferDto;
import pharmacyhub.dto.search.OfferSearchDto;
import pharmacyhub.repositories.OfferRepository;
import pharmacyhub.repositories.specifications.offers.OfferSpecifications;

@Service
public class OfferService {
	@Autowired
	private OfferRepository offerRepository;
	
	private OfferDto getDtoFromEntity(Offer offer) {
		OfferDto dto = new OfferDto();
		dto.setDeliveryDate(offer.getDeliveryDate());
		dto.setDeliveryTime(offer.getDeliveryTime());
		dto.setDrugOrderId(offer.getDrugOrder().getId());
		dto.setPrice(offer.getPrice());
		dto.setStatus(offer.getStatus());

		return dto;
	}
	
	public List<OfferDto> search(OfferSearchDto offerSearchDto, Pageable pageable){
		List<Offer> results = offerRepository.findAll(OfferSpecifications.withSearch(offerSearchDto), pageable).toList();
		return results.stream().map(drugOrder -> getDtoFromEntity(drugOrder)).collect(Collectors.toList());
	}
}
