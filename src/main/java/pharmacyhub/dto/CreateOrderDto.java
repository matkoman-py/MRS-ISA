package pharmacyhub.dto;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

public class CreateOrderDto {
	
	List<SelectedDrugDto> selectedDrugs;
	Date date;
	Time time;
	String drugstoreId;
	
	public CreateOrderDto() {
		
	}
	
	public CreateOrderDto(List<SelectedDrugDto> selectedDrugs, Date date, Time time, String drugstoreId) {
		super();
		this.selectedDrugs = selectedDrugs;
		this.date = date;
		this.time = time;
		this.drugstoreId = drugstoreId;
	}

	public List<SelectedDrugDto> getSelectedDrugs() {
		return selectedDrugs;
	}

	public void setSelectedDrugs(List<SelectedDrugDto> selectedDrugs) {
		this.selectedDrugs = selectedDrugs;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getDrugstoreId() {
		return drugstoreId;
	}

	public void setDrugstoreId(String drugstoreId) {
		this.drugstoreId = drugstoreId;
	}

}
