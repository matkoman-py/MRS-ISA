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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.PharmacyhubApplication;
import pharmacyhub.domain.Drug;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.services.DrugService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PharmacyhubApplication.class)
public class DrugServiceTest {

	@Mock
	private DrugRepository drugRepositoryMock;
	
	@Mock
	private Drug drugMock;
	
	@InjectMocks
	private DrugService drugService;
	
	
	@Test
	public void getOneTest() {
		
			drugMock = new Drug();
			drugMock.setId("d7d2d173-9f15-4bd2-979c-6933746f6be4");
			when(drugRepositoryMock.findById("d7d2d173-9f15-4bd2-979c-6933746f6be4")).thenReturn(Optional.of(drugMock));
			
			Drug retVal = drugService.getOne("d7d2d173-9f15-4bd2-979c-6933746f6be4");
			
			assertEquals(drugMock, retVal);
		
	}
	
	@Test
	public void getOneNameTest() {
		
			drugMock = new Drug();
			drugMock.setName("Zanax");
			when(drugRepositoryMock.findByName("Zanax")).thenReturn((drugMock));
			
			Drug retVal = drugService.getOneName("Zanax");
			
			assertEquals(drugMock, retVal);
		
	}
	
	@Test
	public void update() {
		drugMock = new Drug();
		drugMock.setId("d7d2d173-9f15-4bd2-979c-6933746f6be4");
		when(drugRepositoryMock.findById("d7d2d173-9f15-4bd2-979c-6933746f6be4")).thenReturn(Optional.of(drugMock));
		drugMock.setName("Novo ime 1");
		drugRepositoryMock.save(drugMock);
		
		Drug retVal = drugService.getOne("d7d2d173-9f15-4bd2-979c-6933746f6be4");
		
		assertEquals(drugMock, retVal);
		
		
	}
}
