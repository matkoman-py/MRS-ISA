package pharmacyhub.dto.ereceipt;

import java.util.List;

import pharmacyhub.domain.Drug;

public class ReceiptSearchResultsDto {

	private List<Drug> drugs;
	private List<DrugstoreAndPriceDto> drugstores;

	public List<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}

	public List<DrugstoreAndPriceDto> getDrugstores() {
		return drugstores;
	}

	public void setDrugstores(List<DrugstoreAndPriceDto> drugstores) {
		this.drugstores = drugstores;
	}

}
