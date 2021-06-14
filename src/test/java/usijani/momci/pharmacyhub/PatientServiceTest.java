package usijani.momci.pharmacyhub;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import pharmacyhub.PharmacyhubApplication;
import pharmacyhub.domain.Drug;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.repositories.users.PatientRepository;
import pharmacyhub.services.PatientService;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PharmacyhubApplication.class)
public class PatientServiceTest {
	
	@Mock
	private PatientRepository patientRepositoryMock;
	
	@Mock
	private Patient patientMock;
	
	@InjectMocks
	private PatientService patientService;
	
	
	@Test
	public void getOneTest() {
		
			patientMock = new Patient();
			patientMock.setId("novid");
			patientRepositoryMock.save(patientMock);
			when(patientRepositoryMock.findById("novid")).thenReturn(Optional.of(patientMock));
			
			Patient retVal = patientService.returnPatient("novid");
			
			assertEquals(patientMock, retVal);
		
	}
	
	@Test
	public void update() throws Exception {
		patientMock = new Patient();
		patientMock.setId("novid");
		patientRepositoryMock.save(patientMock);
		when(patientRepositoryMock.findById("novid")).thenReturn(Optional.of(patientMock));
		patientMock.setName("Novo ime 1");
		
		
		Patient retVal = patientService.update(patientMock);
		
		assertEquals(patientMock, retVal);
	}
	
	@Test
	public void getPatients() {
		
		
		Patient a1 = new Patient();
		a1.setId("1");
		Patient a2 = new Patient();
		a2.setId("2");
		patientRepositoryMock.save(a1);
		patientRepositoryMock.save(a2);
		
		
		List<Patient> retVal = patientService.findAll();
		
		assertTrue(retVal.size() == patientRepositoryMock.findAll().size());
	}
}
