package pharmacyhub.controllers;

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

import pharmacyhub.dto.DrugReservationDto;
import pharmacyhub.services.DrugReservationService;

@Controller
@RequestMapping("/concurent")
public class ConcurentTestController {

	@Autowired
	private DrugReservationService drugReservationService;
	
	@PostMapping(path = "/saveReservation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveReservation(@RequestBody DrugReservationDto drugreservationDto) throws Exception {
		
		Runnable request = new Runnable() {

			@Override
			public void run() {
				try {
					drugReservationService.saveReservation(drugreservationDto);
				} catch (MessagingException e) {
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
}
