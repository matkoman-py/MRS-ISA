package pharmacyhub.controllers;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.dto.DermatologistAppointmentDto;
import pharmacyhub.dto.DrugReservationDto;
import pharmacyhub.dto.PharmacistAppointmentPatientDto;
import pharmacyhub.dto.complaint.MakeReplyDto;
import pharmacyhub.services.DermatologistAppointmentService;
import pharmacyhub.services.DrugOrderService;
import pharmacyhub.services.DrugReservationService;
import pharmacyhub.services.PharmacistAppointmentService;
import pharmacyhub.services.complaints.ComplaintService;

@Controller
@RequestMapping("/concurent")
public class ConcurentTestController {

	@Autowired
	private DrugReservationService drugReservationService;
	
	@Autowired
	private DrugOrderService drugOrderService;
	
	@Autowired
	private ComplaintService complaintService;
	
	@Autowired
	private DermatologistAppointmentService dermatologistAppointmentService;
	
	@Autowired
	private PharmacistAppointmentService pharmacistAppointmentService;

	
	@PostMapping(path = "/saveReservation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveReservation(@RequestBody DrugReservationDto drugreservationDto) throws Exception {
		
		Runnable request = new Runnable() {

			@Override
			public void run() {
				try {
					drugReservationService.saveReservation(drugreservationDto);
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		executor.schedule(request, 1000, TimeUnit.MILLISECONDS);
		executor.schedule(request, 4000, TimeUnit.MILLISECONDS);
		executor.schedule(request, 200, TimeUnit.MILLISECONDS);
		executor.schedule(request, 800, TimeUnit.MILLISECONDS);
		executor.schedule(request, 1500, TimeUnit.MILLISECONDS);
		executor.schedule(request, 50, TimeUnit.MILLISECONDS);
		executor.schedule(request, 0, TimeUnit.MILLISECONDS);
		
		executor.shutdown();
		//executor.awaitTermination(1, TimeUnit.MINUTES);
		
		return new ResponseEntity<>("test", HttpStatus.OK);
	}
	
	@PostMapping(path = "/drug-order/accepted", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> acceptOffer() throws Exception {
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		executor.schedule(() -> drugOrderService.orderAccepted("1"), 1000, TimeUnit.MILLISECONDS);
		executor.schedule(() -> drugOrderService.orderAccepted("2"), 1000, TimeUnit.MILLISECONDS);

		executor.shutdown();
		//executor.awaitTermination(1, TimeUnit.MINUTES);
		
		return new ResponseEntity<>("test", HttpStatus.OK);
	}
	
	@PostMapping(path = "/reply", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> testReplies() throws Exception {
		
		var wrapper1 = new Object() {
			MakeReplyDto makeReplyDto = new MakeReplyDto("11038a9a-a754-11eb-bcbc-0242ac130002", "2d2eacbf-fda8-4a50-aa05-be2be4fb2884", "Hej pa ovo je nesto");
			};
			
		var wrapper2 = new Object() {
			MakeReplyDto makeReplyDto = new MakeReplyDto("11038a9a-a754-11eb-bcbc-0242ac130002", "332eacbf-fda8-4a50-aa05-be2be4fb2884", "Hej pa ovo je nesto drugo");
			};
			
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		executor.schedule(() -> complaintService.makeReply(wrapper1.makeReplyDto), 1000, TimeUnit.MILLISECONDS);
		executor.schedule(() -> complaintService.makeReply(wrapper2.makeReplyDto), 1000, TimeUnit.MILLISECONDS);

		executor.shutdown();
		//executor.awaitTermination(1, TimeUnit.MINUTES);
		
		return new ResponseEntity<>("test", HttpStatus.OK);
	}
	
	@PostMapping(path = "/derma", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> testDermatology() throws Exception {
		
		var wrapper1 = new Object() {
			@SuppressWarnings("deprecation")
			Date date = new GregorianCalendar(2021, Calendar.JULY, 18).getTime();
			Time time = Time.valueOf("11:00:00");
			Dermatologist derma = dermatologistAppointmentService.findById("68eec890-3bc5-47e3-8a5b-d3544ebbfeb3");
			DermatologistAppointmentDto dermatologistAppointmentDto = new DermatologistAppointmentDto(
					derma,
					"2b7933e9-6523-463a-974b-ded43ad63843",
					date,
					time,
					60,
					1000
					);
			};
			
			var wrapper2 = new Object() {
				@SuppressWarnings("deprecation")
				Date date = new GregorianCalendar(2021, Calendar.JULY, 18).getTime();
				Time time = Time.valueOf("11:00:00");
				Dermatologist derma = dermatologistAppointmentService.findById("2fasdfe9-6523-463a-974b-ded43ad63843");
				DermatologistAppointmentDto dermatologistAppointmentDto = new DermatologistAppointmentDto(
						derma,
						"2b7933e9-6523-463a-974b-ded43ad63843",
						date,
						time,
						60,
						1000
						);
				};
			
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		executor.schedule(() -> dermatologistAppointmentService.save(wrapper1.dermatologistAppointmentDto), 1000, TimeUnit.MILLISECONDS);
		executor.schedule(() -> dermatologistAppointmentService.save(wrapper2.dermatologistAppointmentDto), 1000, TimeUnit.MILLISECONDS);

		executor.shutdown();
		//executor.awaitTermination(1, TimeUnit.MINUTES);
		
		return new ResponseEntity<>("test", HttpStatus.OK);
	}
	
	@PostMapping(path = "/pharma", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> testPharmacist() throws Exception {
		
		var wrapper1 = new Object() {
			@SuppressWarnings("deprecation")
			Date date = new GregorianCalendar(2021, Calendar.JULY, 18).getTime();
			Time time = Time.valueOf("11:00:00");
			Dermatologist derma = dermatologistAppointmentService.findById("68eec890-3bc5-47e3-8a5b-d3544ebbfeb3");
//			private String pharmacistId;
//			private Date date;
//			private Time time;
//			private int duration;
//			private String patientId;
			PharmacistAppointmentPatientDto pharmacistAppointmentPatientDto = new PharmacistAppointmentPatientDto(
					"9d5b9e63-b86c-4a53-bfbf-fdaaa3f20f27",
					date,
					time,
					20,
					"393dca36-3778-4802-939c-de7a0a265c9d"
					);
			};
			
			var wrapper2 = new Object() {
				@SuppressWarnings("deprecation")
				Date date = new GregorianCalendar(2021, Calendar.JULY, 18).getTime();
				Time time = Time.valueOf("11:00:00");
				Dermatologist derma = dermatologistAppointmentService.findById("2fasdfe9-6523-463a-974b-ded43ad63843");
				PharmacistAppointmentPatientDto pharmacistAppointmentPatientDto = new PharmacistAppointmentPatientDto(
						"9d5b9e63-b86c-4a53-bfbf-fdaaa3f20f27",
						date,
						time,
						20,
						"393dca36-3778-4802-939c-de7a0a265c9d"
						);
				};
			
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		executor.schedule(() -> pharmacistAppointmentService.saveWithPatient(wrapper1.pharmacistAppointmentPatientDto), 1000, TimeUnit.MILLISECONDS);
		executor.schedule(() -> pharmacistAppointmentService.saveWithPatient(wrapper2.pharmacistAppointmentPatientDto), 1000, TimeUnit.MILLISECONDS);

		executor.shutdown();
		//executor.awaitTermination(1, TimeUnit.MINUTES);
		
		return new ResponseEntity<>("test", HttpStatus.OK);
	}
}
