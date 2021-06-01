package pharmacyhub.dto;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import pharmacyhub.domain.ColumnInfo;

public class ReportDto {
	
	private Object columnChart;
	private HashMap<String, Integer> pieChart;
	private Date startDate;
	private Date endDate;
	
	public ReportDto() {
		
	}
	
	public ReportDto(Object columnChart, HashMap<String, Integer> pieChart, Date startDate, Date endDate) {
		super();
		this.columnChart = columnChart;
		this.pieChart = pieChart;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Object getColumnChart() {
		return columnChart;
	}

	public void setColumnChart(Object columnChart) {
		this.columnChart = columnChart;
	}

	public HashMap<String, Integer> getPieChart() {
		return pieChart;
	}

	public void setPieChart(HashMap<String, Integer> pieChart) {
		this.pieChart = pieChart;
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

}
