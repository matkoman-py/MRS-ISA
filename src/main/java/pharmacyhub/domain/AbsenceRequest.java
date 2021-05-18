package pharmacyhub.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import pharmacyhub.domain.users.Employee;
import pharmacyhub.domain.users.User;

@Entity
@SQLDelete(sql = "UPDATE absence_request SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class AbsenceRequest extends BaseEntity {
	
	@Column(nullable = false)
	public String reason;
	@Column(nullable = false)
	private Date startDate;
	@Column(nullable = false)
	private Date endDate;
	@Column(nullable = false)
	private boolean accepted;
	@ManyToOne
	@JoinColumn(name = "emlpoyee_fk", nullable = false)
	private User employee;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public User getEmployee() {
		return employee;
	}
	public void setEmployee(User employee) {
		this.employee = employee;
	}
	public AbsenceRequest() {
		super();
	}
	public AbsenceRequest(String reason, Date startDate, Date endDate,  User employee) {
		super();
		this.reason = reason;
		this.startDate = startDate;
		this.endDate = endDate;
		this.accepted = false;
		this.employee = employee;
	}
	
	
	
}
