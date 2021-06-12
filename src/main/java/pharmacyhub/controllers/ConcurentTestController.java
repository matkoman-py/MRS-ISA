package pharmacyhub.controllers;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.dto.DrugReservationDto;
import pharmacyhub.services.DrugReservationService;

@Controller
@RequestMapping("/concurent")
public class ConcurentTestController {

	@Autowired
	private DrugReservationService drugReservationService;
	
	@PostMapping(path = "/saveReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveReservation(@RequestBody DrugReservationDto drugreservationDto) throws Exception {
		Thread request1 = new Thread() {
			public void run() {
				try {
					drugReservationService.saveReservation(drugreservationDto);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		};
		Thread request2 = new Thread() {
			public void run() {
				try {
					drugReservationService.saveReservation(drugreservationDto);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		};
		
		request1.start();
		request2.start();
		
		return new ResponseEntity<>("test", HttpStatus.OK);
	}
}
