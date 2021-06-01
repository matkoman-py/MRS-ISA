package pharmacyhub.dto.complaint;

public class MakeReplyDto {

	private String complaintId;
	private String adminId;
	private String text;

	public MakeReplyDto() {
		super();
	}

	public MakeReplyDto(String complaintId, String adminId, String text) {
		super();
		this.complaintId = complaintId;
		this.adminId = adminId;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
