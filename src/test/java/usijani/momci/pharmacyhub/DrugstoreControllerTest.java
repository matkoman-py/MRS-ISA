package usijani.momci.pharmacyhub;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pharmacyhub.PharmacyhubApplication;
import pharmacyhub.domain.Drug;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Location;
import pharmacyhub.domain.Point;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PharmacyhubApplication.class)
@AutoConfigureMockMvc
public class DrugstoreControllerTest {

	private static final String URL_PREFIX = "/drugstores";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testGetAllDrugstores()  {
		try {
			mockMvc.perform(get(URL_PREFIX)).andExpect(status().isOk());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetDrugstore() {
		try {
			mockMvc.perform(get(URL_PREFIX+"/2b7933e9-6523-463a-974b-ded43ad63843")).andExpect(status().isOk());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteDrugstore() throws Exception {
		
		try {
			this.mockMvc.perform(delete(URL_PREFIX+"/2b7933e9-6523-463a-974b-ded43ad63843")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
