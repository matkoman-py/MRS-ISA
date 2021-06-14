package usijani.momci.pharmacyhub;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import pharmacyhub.PharmacyhubApplication;
import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.repositories.DermatologistAppointmentRepository;
import pharmacyhub.services.DermatologistAppointmentService;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PharmacyhubApplication.class)
class DermatologistAppointmentServiceTest {

	@Mock
	private DermatologistAppointmentRepository dermatologistAppointmentRepositoryMock;
	
	@Mock
	private DermatologistAppointment dermatologistAppointment;
	
	@InjectMocks
	private DermatologistAppointmentService dermatologistAppointmentService;
	
	@Test
	public void getOneTest() {
		
			dermatologistAppointment = new DermatologistAppointment();
			dermatologistAppointment.setId("novid");
			dermatologistAppointmentRepositoryMock.save(dermatologistAppointment);
			when(dermatologistAppointmentRepositoryMock.findById("novid")).thenReturn(Optional.of(dermatologistAppointment));
			
			DermatologistAppointment retVal = dermatologistAppointmentService.findAppointment("novid");
			
			assertEquals(dermatologistAppointment, retVal);
		
	}
	
	@Test
	public void getOneProcessed() {
		dermatologistAppointment = new DermatologistAppointment();
		dermatologistAppointment.setId("novid");
		Dermatologist d = new Dermatologist();
		d.setId("d");
		dermatologistAppointment.setDermatologist(d);
		dermatologistAppointment.setProcessed(true);
		dermatologistAppointmentRepositoryMock.save(dermatologistAppointment);
		
		int retVal = dermatologistAppointmentService.findAllDermatologistAppointmentsDoneLength("d");
		assertTrue(retVal == dermatologistAppointmentRepositoryMock.findByDermatologistIdAndProcessedTrue("d").size());
	}
	
	@Test
	public void getAppointments() {
		DermatologistAppointment a1 = new DermatologistAppointment();
		a1.setId("1");
		DermatologistAppointment a2 = new DermatologistAppointment();
		a2.setId("2");
		dermatologistAppointmentRepositoryMock.save(a1);
		dermatologistAppointmentRepositoryMock.save(a2);
		
		
		List<DermatologistAppointment> retVal = dermatologistAppointmentService.findAll();
		
		assertTrue(retVal.size() == dermatologistAppointmentRepositoryMock.findAll().size());
	}
}
