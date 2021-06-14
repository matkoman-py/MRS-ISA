package pharmacyhub.services;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.DrugOrder;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Offer;
import pharmacyhub.domain.OrderStock;
import pharmacyhub.domain.enums.OfferStatus;
import pharmacyhub.domain.enums.OrderStatus;
import pharmacyhub.dto.CreateOrderDto;
import pharmacyhub.dto.DrugOrderOverviewDto;
import pharmacyhub.dto.SelectedDrugDto;
import pharmacyhub.dto.drugOrder.DrugOrderDto;
import pharmacyhub.dto.search.DrugOrderSearchDto;
import pharmacyhub.repositories.DrugOrderRepository;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugRequestRepository;
import pharmacyhub.repositories.DrugStockRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.OfferRepository;
import pharmacyhub.repositories.OrderStockRepository;
import pharmacyhub.repositories.specifications.drugOrders.DrugOrderSpecifications;

@Service
@Transactional
public class DrugOrderService {

	@Autowired
	private DrugOrderRepository drugOrderRepository;
	
	@Autowired
	private DrugRepository drugRepository;
	
	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired
	private OrderStockRepository orderStockRepository;
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private DrugStockRepository drugStockRepository;
	
	@Autowired
	private DrugRequestRepository drugRequestRepository;
	
	@Autowired
	private UserNotificationService userNotificationService;
	
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
		DrugOrder drugOrder = new DrugOrder(drugOrders, createOrderDto.getDate(), createOrderDto.getTime(), OrderStatus.Active, drugstoreRepository.findById(createOrderDto.getDrugstoreId()).orElse(null));
		drugOrderRepository.save(drugOrder);
		return "Order created successfully!";
	}

	public List<DrugOrderOverviewDto> getOrdersForDrugstore(String drugstoreId, String filter) {
		Drugstore drugstore = drugstoreRepository.findById(drugstoreId).orElse(null);
		List<DrugOrder> orders = drugOrderRepository.findByDrugstore(drugstore);
		List<DrugOrderOverviewDto> returnValue = new ArrayList<DrugOrderOverviewDto>();
		for (DrugOrder order : orders) {
			if (order.getStatus() == OrderStatus.Active) {
				long millis=System.currentTimeMillis();
				Date date=new Date(millis);
				Time time=new Time(millis);
				if (order.getDeadlineDate().before(date) || (order.getDeadlineDate().equals(date) && order.getDeadlineTime().before(time))) {
					order.setStatus(OrderStatus.Pending);
				}
			}
			
			if (filter.equals("All_orders") || filter.equals(order.getStatus().toString())) {
				String drugs = "";
				for (OrderStock orderStock : order.getStock()) {
					drugs += orderStock.getDrug().getName() + " (" + orderStock.getAmount() + "), ";
				}
				drugs = drugs.substring(0, drugs.length() - 2);
				returnValue.add(new DrugOrderOverviewDto(drugs, order.getStatus(), order.getDeadlineDate(), order.getDeadlineTime(), order.getId()));
			}
		}
		return returnValue;
	}

	public Boolean orderExpired(String orderId) {
		DrugOrder order = drugOrderRepository.findById(orderId).orElse(null);
		order.setStatus(OrderStatus.Expired);
		drugOrderRepository.save(order);
		return true;
	}

	@Transactional(readOnly = false)
	public Boolean orderAccepted(String offerId) throws Exception {
		
		Offer offer = offerRepository.findByIdAndStatus(offerId, OfferStatus.Pending);
				
		if(offer == null) {
			throw new Exception("Offer already processed!");
		}
		
		DrugOrder order = drugOrderRepository.findById(offer.getDrugOrder().getId()).orElse(null);
		List<Offer> offers = offerRepository.findByDrugOrderOrderByIdAsc(order);
		for (Offer o : offers) {
			if(o.getId().equals(offerId)) {
				changeOfferStatus(o.getId(), OfferStatus.Accepted);
			}else {
				changeOfferStatus(o.getId(), OfferStatus.Rejected);
			}
		}
		
		for (Offer o : offers) {
			userNotificationService.notifySupplier(o);
		}
		
		order.setStatus(OrderStatus.Accepted);
		Drugstore drugstore = order.getDrugstore();
		for (OrderStock stock : order.getStock()) {
			updateDrugStockAmount(stock, drugstore);
			//logicki obrisati requestove za ove lekove koji su naruceni
			drugRequestRepository.deleteByDrugstoreAndDrug(drugstore, stock.getDrug());	
		}
		drugOrderRepository.save(order);
		return true;
	}
	
	@Transactional(readOnly = false)
	public void changeOfferStatus(String offerId, OfferStatus offerStatus) {
		Offer offer = offerRepository.findByIdAndStatus(offerId, OfferStatus.Pending);
		//System.out.println("Id: " + offerId + " ver: " + offer.getVersion() + ", from " + offer.getStatus() + " to: " + offerStatus);
		offer.setStatus(offerStatus);
		offerRepository.save(offer);
	}
	
	public void acceptOffer(String offerId) throws Exception {
		try {
			Offer offer = offerRepository.findByIdAndStatus(offerId, OfferStatus.Pending);
			
			if(offer == null) {
				System.out.println("Sacemo videti");
				throw new Exception("Offer already accepted or rejected");
			}
			
			offer.setStatus(OfferStatus.Accepted);
			offerRepository.save(offer);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Transactional(readOnly = false)
	public void updateDrugStockAmount(OrderStock stock, Drugstore drugstore) {
		DrugStock drugStock = drugStockRepository.findByDrugAndDrugstore(stock.getDrug(), drugstore);
		drugStock.setAmount(drugStock.getAmount() + stock.getAmount());
		drugStockRepository.save(drugStock);
	}

	public Boolean orderDeclined(String orderId) throws MessagingException {
		DrugOrder order = drugOrderRepository.findByIdAndStatus(orderId, OrderStatus.Pending);
		List<Offer> offers = offerRepository.findByDrugOrderOrderByIdAsc(order);
		for (Offer o : offers) {
			o.setStatus(OfferStatus.Rejected);
			offerRepository.save(o);
			userNotificationService.notifySupplier(o);
		}
		order.setStatus(OrderStatus.Declined);
		drugOrderRepository.save(order);
		return true;
	}
}
