package pharmacyhub.controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.dto.ReportDto;
import pharmacyhub.services.ReportService;

@Controller
@RequestMapping("/reports")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN')")
	@GetMapping(path="/month", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReportDto> getMonthReport(@RequestParam String drugstoreId, @RequestParam int currentYear, @RequestParam int direction) throws Exception {
		return new ResponseEntity<>(reportService.getMonthReport(drugstoreId, currentYear, direction), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN')")
	@GetMapping(path="/quartal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReportDto> getQuartalReport(@RequestParam String drugstoreId, @RequestParam int currentYear, @RequestParam int direction) throws Exception {
		return new ResponseEntity<>(reportService.getQuartalReport(drugstoreId, currentYear, direction), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('DRUGSTOREADMIN')")
	@GetMapping(path="/year", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReportDto> getYearReport(@RequestParam String drugstoreId, @RequestParam int currentYear, @RequestParam int direction) throws Exception {
		return new ResponseEntity<>(reportService.getYearReport(drugstoreId, currentYear, direction), HttpStatus.OK);
	}

}
