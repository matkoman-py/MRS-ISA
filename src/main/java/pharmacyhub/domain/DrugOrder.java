package pharmacyhub.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;

import pharmacyhub.domain.enums.OrderStatus;

@Entity
@Table(name = "drug_order")
@SQLDelete(sql = "UPDATE drug_order SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class DrugOrder extends BaseEntity {

	@OneToMany
	private List<OrderStock> stock;

	@Column(nullable = false)
	private Date deadlineDate;

	@Column(nullable = false)
	private Time deadlineTime;

	@Column(nullable = false)
	private OrderStatus status;

	@ManyToOne
	private Drugstore drugstore;
	
	public DrugOrder() {
		
	}
	
	public DrugOrder(List<OrderStock> stock, Date deadlineDate, Time deadlineTime, OrderStatus status,
			Drugstore drugstore) {
		super();
		this.stock = stock;
		this.deadlineDate = deadlineDate;
		this.deadlineTime = deadlineTime;
		this.status = status;
		this.drugstore = drugstore;
	}

	public List<OrderStock> getStock() {
		return stock;
	}

	public void setStock(List<OrderStock> stock) {
		this.stock = stock;
	}

	public Date getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public Time getDeadlineTime() {
		return deadlineTime;
	}

	public void setDeadlineTime(Time deadlineTime) {
		this.deadlineTime = deadlineTime;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Drugstore getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
	}

}
