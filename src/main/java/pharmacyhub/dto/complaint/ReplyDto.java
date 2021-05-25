package pharmacyhub.dto.complaint;

import pharmacyhub.domain.complaints.Reply;

public class ReplyDto {

	private String text;
	private String complaintId;
	private String adminId;
	private String adminName;
	private String adminSurname;

	public ReplyDto() {
		super();
	}

	public ReplyDto(Reply reply) {
		super();
		this.text = reply.getText();
		this.complaintId = reply.getComplaint().getId();
		this.adminId = reply.getSystemAdmin().getId();
		this.adminName = reply.getSystemAdmin().getName();
		this.adminSurname = reply.getSystemAdmin().getSurname();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminSurname() {
		return adminSurname;
	}

	public void setAdminSurname(String adminSurname) {
		this.adminSurname = adminSurname;
	}

}
