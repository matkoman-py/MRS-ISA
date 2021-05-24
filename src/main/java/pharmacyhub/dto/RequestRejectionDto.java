package pharmacyhub.dto;

public class RequestRejectionDto {
	
	private String requestId;
	private String reasonOfRejection;
	
	private RequestRejectionDto() {
		
	}

	public RequestRejectionDto(String requestId, String reasonOfRejection) {
		super();
		this.requestId = requestId;
		this.reasonOfRejection = reasonOfRejection;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getReasonOfRejection() {
		return reasonOfRejection;
	}

	public void setReasonOfRejection(String reasonOfRejection) {
		this.reasonOfRejection = reasonOfRejection;
	}

}
