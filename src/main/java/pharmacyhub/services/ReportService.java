package pharmacyhub.services;

import java.sql.Date;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.ColumnInfo;
import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.DrugReservation;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.dto.ReportDto;
import pharmacyhub.repositories.DermatologistAppointmentRepository;
import pharmacyhub.repositories.DrugReservationRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.PharmacistAppointmentRepository;

@Service
public class ReportService {
	
	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired
	private DrugReservationRepository drugReservationRepository;
	
	@Autowired
	private PharmacistAppointmentRepository pharmacistAppointmentRepository;
	
	@Autowired
	private DermatologistAppointmentRepository dermatologistAppointmentRepository;

	public ReportDto getMonthReport(String drugstoreId, int currentYear, int direction) {
		List<Integer> columnChart = new ArrayList<>();
		HashMap<String, Integer> pieChart = new HashMap<>();
		int year = currentYear + direction;
		Drugstore drugstore = drugstoreRepository.findById(drugstoreId).orElse(null);
		
		List<String> monthSeparators = new ArrayList<String>();
		for (int i = 1; i < 14; i++) {
			if (i < 10)
				monthSeparators.add(year + "-0" + i + "-01");
			else if (i < 13)
				monthSeparators.add(year + "-" + i + "-01");
			else
				monthSeparators.add(year + 1 + "-01-01");
		}
		
		int drugProfitInTotal = 0;
		int pharmacistsProfitInTotal = 0;
		int dermatologistsProfitInTotal = 0;
		
		for (int i = 0; i < 12; i ++) {
			Date startDate = Date.valueOf(monthSeparators.get(i));
			Date endDate = Date.valueOf(monthSeparators.get(i+1));
			
			int drugProfit = getDrugReservationProfit(startDate, endDate, drugstore);
			int pharmacistsProfit = getPharmacistAppointmentsProfit(startDate, endDate, drugstore);
			int dermatologistsProfit = getDermatologistAppointmentsProfit(startDate, endDate, drugstore);
			
			drugProfitInTotal += drugProfit;
			pharmacistsProfitInTotal += pharmacistsProfit;
			dermatologistsProfitInTotal += dermatologistsProfit;
			
			columnChart.add(drugProfit + pharmacistsProfit + dermatologistsProfit);
		}
		if (drugProfitInTotal + pharmacistsProfitInTotal + dermatologistsProfitInTotal == 0)
			return null;
		
		pieChart.put("Drug reservations", drugProfitInTotal);
		pieChart.put("Pharmacist appointments", pharmacistsProfitInTotal);
		pieChart.put("Dermatologist appointments", dermatologistsProfitInTotal);
		
		return new ReportDto(columnChart, pieChart, Date.valueOf(monthSeparators.get(0)), Date.valueOf(monthSeparators.get(12)));
	}
	
	public ReportDto getQuartalReport(String drugstoreId, int currentYear, int direction) {
		HashMap<String, Integer> columnChart = new HashMap<>();
		HashMap<String, Integer> pieChart = new HashMap<>();
		int year = currentYear + direction;
		Drugstore drugstore = drugstoreRepository.findById(drugstoreId).orElse(null);
		
		List<String> monthSeparators = new ArrayList<String>();
		monthSeparators.add(year + "-01-01");
		monthSeparators.add(year + "-04-01");
		monthSeparators.add(year + "-07-01");
		monthSeparators.add(year + "-10-01");
		monthSeparators.add(year + 1 + "-01-01");
		
		int drugProfitInTotal = 0;
		int pharmacistsProfitInTotal = 0;
		int dermatologistsProfitInTotal = 0;
		
		for (int i = 0; i < 4; i ++) {
			Date startDate = Date.valueOf(monthSeparators.get(i));
			Date endDate = Date.valueOf(monthSeparators.get(i+1));
			
			int drugProfit = getDrugReservationProfit(startDate, endDate, drugstore);
			int pharmacistsProfit = getPharmacistAppointmentsProfit(startDate, endDate, drugstore);
			int dermatologistsProfit = getDermatologistAppointmentsProfit(startDate, endDate, drugstore);
			
			drugProfitInTotal += drugProfit;
			pharmacistsProfitInTotal += pharmacistsProfit;
			dermatologistsProfitInTotal += dermatologistsProfit;
			
			String columnString = "";
			if (i == 0)
				columnString = "Q1";
			else if (i == 1)
				columnString = "Q2";
			else if (i == 2)
				columnString = "Q3";
			else
				columnString = "Q4";
			
			columnChart.put(columnString, drugProfit + pharmacistsProfit + dermatologistsProfit);
		}
		if (drugProfitInTotal + pharmacistsProfitInTotal + dermatologistsProfitInTotal == 0)
			return null;
		
		pieChart.put("Drug reservations", drugProfitInTotal);
		pieChart.put("Pharmacist appointments", pharmacistsProfitInTotal);
		pieChart.put("Dermatologist appointments", dermatologistsProfitInTotal);
		
		return new ReportDto(columnChart, pieChart, Date.valueOf(monthSeparators.get(0)), Date.valueOf(monthSeparators.get(4)));
	}

	public ReportDto getYearReport(String drugstoreId, int currentYear, int direction) {
		HashMap<String, Integer> columnChart = new HashMap<>();
		HashMap<String, Integer> pieChart = new HashMap<>();
		int year = currentYear + direction;
		Drugstore drugstore = drugstoreRepository.findById(drugstoreId).orElse(null);
		
		List<String> monthSeparators = new ArrayList<String>();
		for (int i = 0; i < 11; i++) {
			monthSeparators.add(year + i + "-01-01");
		}
		
		int drugProfitInTotal = 0;
		int pharmacistsProfitInTotal = 0;
		int dermatologistsProfitInTotal = 0;
		
		for (int i = 0; i < 10; i ++) {
			Date startDate = Date.valueOf(monthSeparators.get(i));
			Date endDate = Date.valueOf(monthSeparators.get(i+1));
			
			int drugProfit = getDrugReservationProfit(startDate, endDate, drugstore);
			int pharmacistsProfit = getPharmacistAppointmentsProfit(startDate, endDate, drugstore);
			int dermatologistsProfit = getDermatologistAppointmentsProfit(startDate, endDate, drugstore);
			
			drugProfitInTotal += drugProfit;
			pharmacistsProfitInTotal += pharmacistsProfit;
			dermatologistsProfitInTotal += dermatologistsProfit;
			
			columnChart.put((year + i) + "", drugProfit + pharmacistsProfit + dermatologistsProfit);
		}
		if (drugProfitInTotal + pharmacistsProfitInTotal + dermatologistsProfitInTotal == 0)
			return null;
		
		pieChart.put("Drug reservations", drugProfitInTotal);
		pieChart.put("Pharmacist appointments", pharmacistsProfitInTotal);
		pieChart.put("Dermatologist appointments", dermatologistsProfitInTotal);
		
		return new ReportDto(columnChart, pieChart, Date.valueOf(monthSeparators.get(0)), Date.valueOf(monthSeparators.get(10)));
	}
	
	private int getDrugReservationProfit(Date from, Date to, Drugstore drugstore) {
		List<DrugReservation> drugsSold = drugReservationRepository.findByDrugstoreAndIssuedTrue(drugstore);
		int profit = 0;
		for (DrugReservation reservation : drugsSold) {
			Date date = Date.valueOf(reservation.getDate());
			if ((date.after(from) || from == date) && (date.before(to) || to == date))
				profit += reservation.getPrice();

		}
		return profit;
	}
	
	private int getPharmacistAppointmentsProfit(Date from, Date to, Drugstore drugstore) {
		List<PharmacistAppointment> appointments = pharmacistAppointmentRepository.findByPharmacistDrugstoreAndProcessedTrue(drugstore);
		int profit = 0;
		for (PharmacistAppointment appointment : appointments) {
			if (appointment.getAppointmentReport() != null) {
				if (appointment.getAppointmentReport().equals("Appointment canceled due to pharmacist absence.") || appointment.getAppointmentReport().equals("Patient did not show up!"))
					continue;
			}
			if ((appointment.getDate().after(from) || from == appointment.getDate()) && (appointment.getDate().before(to) || to == appointment.getDate()))
				profit += appointment.getPrice();
		}
		return profit;
	}

	private int getDermatologistAppointmentsProfit(Date from, Date to, Drugstore drugstore) {
		List<DermatologistAppointment> appointments = dermatologistAppointmentRepository.findByDrugstoreAndProcessedTrue(drugstore);
		int profit = 0;
		for (DermatologistAppointment appointment : appointments) {
			if (appointment.getAppointmentReport() != null) {
				if (appointment.getAppointmentReport().equals("Appointment canceled due to dermatologist absence.") || appointment.getAppointmentReport().equals("Patient did not show up!"))
					continue;
			}
			if ((appointment.getDate().after(from) || from == appointment.getDate()) && (appointment.getDate().before(to) || to == appointment.getDate()))
				profit += appointment.getPrice();
		}
		return profit;
	}

}
