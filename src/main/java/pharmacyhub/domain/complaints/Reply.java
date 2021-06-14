package pharmacyhub.domain.complaints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import pharmacyhub.domain.BaseEntity;
import pharmacyhub.domain.users.User;

@Entity
public class Reply extends BaseEntity {
	
	@OneToOne
	private Complaint complaint;

	@ManyToOne
	private User systemAdmin;

	@Column
	private String text;

	public Reply() {
		super();
	}
	
	public Reply(Complaint complaint, User systemAdmin, String text) {
		super();
		this.complaint = complaint;
		this.systemAdmin = systemAdmin;
		this.text = text;
	}

	public Reply(String id, Complaint complaint, User systemAdmin, String text) {
		super();
		this.id = id;
		this.complaint = complaint;
		this.systemAdmin = systemAdmin;
		this.text = text;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public User getSystemAdmin() {
		return systemAdmin;
	}

	public void setSystemAdmin(User systemAdmin) {
		this.systemAdmin = systemAdmin;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
