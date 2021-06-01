package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "UPDATE penalty SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Penalty extends BaseEntity{
	
	@Column(nullable = false, unique=true)
	private String reservationId;
	
	@Column(nullable = false)
	private String patientId;

	@Column(nullable = false)
	private String type;

	public Penalty(String reservationId, String patientId, String type) {
		super();
		this.reservationId = reservationId;
		this.patientId = patientId;
		this.type = type;
	}

	public Penalty() {
		super();
	}

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
