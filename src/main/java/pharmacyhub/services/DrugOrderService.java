package pharmacyhub.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DrugOrder;
import pharmacyhub.dto.drugOrder.DrugOrderDto;
import pharmacyhub.dto.search.DrugOrderSearchDto;
import pharmacyhub.repositories.DrugOrderRepository;
import pharmacyhub.repositories.specifications.drugOrders.DrugOrderSpecifications;

@Service
public class DrugOrderService {

	@Autowired
	private DrugOrderRepository drugOrderRepository;
	
	private DrugOrderDto getDtoFromEntity(DrugOrder drugOrder) {
		DrugOrderDto dto = new DrugOrderDto();
		dto.setDeadlineDate(drugOrder.getDeadlineDate());
		dto.setDeadlineTime(drugOrder.getDeadlineTime());
		dto.setDrugstoreId(drugOrder.getDrugstore().getId());
		dto.setDrugstoreName(drugOrder.getDrugstore().getName());
		dto.setStatus(drugOrder.getStatus() != null ? drugOrder.getStatus() : null);
		dto.setStock(drugOrder.getStock());
		dto.setId(drugOrder.getId());

		return dto;
	}
	
	public List<DrugOrderDto> search(DrugOrderSearchDto drugOrderSearchDto, Pageable pageable){
		List<DrugOrder> results = drugOrderRepository.findAll(DrugOrderSpecifications.withSearch(drugOrderSearchDto), pageable).toList();
		return results.stream().map(drugOrder -> getDtoFromEntity(drugOrder)).collect(Collectors.toList());
	}
}
