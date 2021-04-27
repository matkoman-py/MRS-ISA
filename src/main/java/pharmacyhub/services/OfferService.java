package pharmacyhub.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DrugOrder;
import pharmacyhub.domain.Offer;
import pharmacyhub.domain.OrderStock;
import pharmacyhub.domain.SupplierStock;
import pharmacyhub.domain.enums.OfferStatus;
import pharmacyhub.domain.users.Supplier;
import pharmacyhub.dto.offer.OfferDto;
import pharmacyhub.dto.search.OfferSearchDto;
import pharmacyhub.repositories.DrugOrderRepository;
import pharmacyhub.repositories.OfferRepository;
import pharmacyhub.repositories.SupplierStockRepository;
import pharmacyhub.repositories.specifications.offers.OfferSpecifications;
import pharmacyhub.repositories.users.SupplierRepository;

@Service
public class OfferService {
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private DrugOrderRepository drugOrderRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private SupplierStockRepository supplierStockRepository;
	
	private OfferDto getDtoFromEntity(Offer offer) {
		OfferDto dto = new OfferDto();
		dto.setDeliveryDate(offer.getDeliveryDate());
		dto.setDeliveryTime(offer.getDeliveryTime());
		dto.setDrugOrderId(offer.getDrugOrder().getId());
		dto.setPrice(offer.getPrice());
		dto.setStatus(offer.getStatus() != null ? offer.getStatus() : null);


		return dto;
	}
	
	public List<OfferDto> search(OfferSearchDto offerSearchDto, Pageable pageable){
		List<Offer> results = offerRepository.findAll(OfferSpecifications.withSearch(offerSearchDto), pageable).toList();
		return results.stream().map(offer -> getDtoFromEntity(offer)).collect(Collectors.toList());
	}
	
	public OfferDto makeOffer(OfferDto offerDto) throws Exception {
		
		DrugOrder drugOrder = drugOrderRepository.findById(offerDto.getDrugOrderId()).orElse(null);
		Supplier supplier = supplierRepository.findById(offerDto.getSupplierId()).orElse(null);

		
		if (drugOrder == null) {
			throw new Exception("Drug order doesn't exist");
		}
		
		if (supplier == null) {
			throw new Exception("Supplier doesn't exist");
		}
		
		if (!checkIfSupplierHasInStock(drugOrder.getStock(), supplier)) {
			throw new Exception("Supplier doesn't have the needed drugs in stock");
		}
		
		Offer offer = new Offer();
		offer.setDeliveryDate(offerDto.getDeliveryDate());
		offer.setDeliveryTime(offerDto.getDeliveryTime());
		offer.setDrugOrder(drugOrder);
		offer.setDeliveryDate(offerDto.getDeliveryDate());
		offer.setDeliveryDate(offerDto.getDeliveryDate());
		offer.setSupplier(supplier);
		offer.setStatus(OfferStatus.Pending);
		offer.setPrice(offerDto.getPrice());
		
		subtractFromSupplierStock(drugOrder.getStock(), supplier);
		
		return getDtoFromEntity(offerRepository.save(offer));
	}
	
	private boolean checkIfSupplierHasInStock(List<OrderStock> stock, Supplier supplier) {
		for(OrderStock orderStock : stock) {
			SupplierStock supplierStock = supplierStockRepository.findByDrugAndSupplier(orderStock.getDrug(), supplier);
			if (supplierStock == null || supplierStock.getAmount() < orderStock.getAmount()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean subtractFromSupplierStock(List<OrderStock> stock, Supplier supplier) {
		for(OrderStock orderStock : stock) {
			SupplierStock supplierStock = supplierStockRepository.findByDrugAndSupplier(orderStock.getDrug(), supplier);
			supplierStock.setAmount(supplierStock.getAmount() - orderStock.getAmount());
			supplierStockRepository.save(supplierStock);
		}
		return true;
	}
}
