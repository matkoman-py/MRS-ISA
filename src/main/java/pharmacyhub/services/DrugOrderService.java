package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DrugOrder;
import pharmacyhub.domain.OrderStock;
import pharmacyhub.domain.enums.OrderStatus;
import pharmacyhub.dto.CreateOrderDto;
import pharmacyhub.dto.SelectedDrugDto;
import pharmacyhub.dto.drugOrder.DrugOrderDto;
import pharmacyhub.dto.search.DrugOrderSearchDto;
import pharmacyhub.repositories.DrugOrderRepository;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.OrderStockRepository;
import pharmacyhub.repositories.specifications.drugOrders.DrugOrderSpecifications;

@Service
public class DrugOrderService {

	@Autowired
	private DrugOrderRepository drugOrderRepository;
	
	@Autowired
	private DrugRepository drugRepository;
	
	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired
	private OrderStockRepository orderStockRepository;
	
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

	public String createNewOrder(CreateOrderDto createOrderDto) {
		List<OrderStock> drugOrders = new ArrayList<OrderStock>();
		for (SelectedDrugDto sd : createOrderDto.getSelectedDrugs()) {
			OrderStock os = new OrderStock(drugRepository.findByName(sd.getName()), Integer.parseInt(sd.getAmount()));
			orderStockRepository.save(os);
			drugOrders.add(os);
		}
		System.out.println("Sacuvan");
		DrugOrder drugOrder = new DrugOrder(drugOrders, createOrderDto.getDate(), createOrderDto.getTime(), OrderStatus.Active, drugstoreRepository.findById(createOrderDto.getDrugstoreId()).orElse(null));
		drugOrderRepository.save(drugOrder);
		return "Order created successfully!";
	}
}
