package pharmacyhub.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import pharmacyhub.domain.enums.OrderStatus;

@Entity
@Table(name = "drug_order")
@SQLDelete(sql = "UPDATE drug_order SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class DrugOrder extends BaseEntity {

	@OneToMany // (mappedBy="order")
	private List<OrderStock> stock;

	@Column(nullable = false)
	private Date deadlineDate;

	@Column(nullable = false)
	private Time deadlineTime;

	@Column(nullable = false)
	private OrderStatus status;

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

}
