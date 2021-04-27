package pharmacyhub.dto.search;

import pharmacyhub.domain.enums.OfferStatus;

public class OfferSearchDto {

	private String supplierId;
	private OfferStatus offerStatus;

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public OfferStatus getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}

}
