package usijani.momci.pharmacyhub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import pharmacyhub.PharmacyhubApplication;
import pharmacyhub.domain.Drug;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.services.DrugstoreService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PharmacyhubApplication.class)
public class DrugstoreServiceTest {

	@Mock
	private DrugstoreRepository drugstoreRepositoryMock;
	
	@Mock
	private Drugstore drugstoreMock;
	
	@InjectMocks
	private DrugstoreService drugService;
	
	
	@Test
	public void getOneTest() {
		
			drugstoreMock = new Drugstore();
			drugstoreMock.setId("2b7933e9-6523-463a-974b-ded43ad63843");
			when(drugstoreRepositoryMock.findById("2b7933e9-6523-463a-974b-ded43ad63843")).thenReturn(Optional.of(drugstoreMock));
			
			Drugstore retVal = drugService.findDrugstore("2b7933e9-6523-463a-974b-ded43ad63843");
			
			assertEquals(drugstoreMock, retVal);
		
	}
	
	@Test
	public void update() {
		drugstoreMock = new Drugstore();
		drugstoreMock.setId("2b7933e9-6523-463a-974b-ded43ad63843");
		when(drugstoreRepositoryMock.findById("2b7933e9-6523-463a-974b-ded43ad63843")).thenReturn(Optional.of(drugstoreMock));
		drugstoreMock.setName("Novo ime 1");
		drugstoreRepositoryMock.save(drugstoreMock);
		
		Drugstore retVal = drugService.findDrugstore("2b7933e9-6523-463a-974b-ded43ad63843");
		
		assertEquals(drugstoreMock, retVal);
	}
	
	@Test
	public void delete() {
		drugstoreMock = new Drugstore();
		drugstoreMock.setId("novid");
		drugstoreRepositoryMock.save(drugstoreMock);
		Drugstore retVal = drugService.findDrugstore("novid");
		drugstoreRepositoryMock.delete(retVal);
		when(drugstoreRepositoryMock.findById("novid")).thenReturn(Optional.of(drugstoreMock));
		assertTrue(drugstoreMock != retVal);
	}

}
