package usijani.momci.pharmacyhub;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import pharmacyhub.domain.users.Patient;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PharmacyhubApplication.class)
@AutoConfigureMockMvc
public class PatientControllerTest {
	
	private static final String URL_PREFIX = "/patients";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetPatient() {
		try {
			mockMvc.perform(get(URL_PREFIX+"/id?patientId=8128d806-c29b-4086-aae6-877d17eeb6fa")).andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$.[*].id").value(hasItem("8128d806-c29b-4086-aae6-877d17eeb6fa")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdatePatient() {
		Patient patient = new Patient();
		patient.setId("8128d806-c29b-4086-aae6-877d17eeb6fa");
		patient.setName("pera");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(patient);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		try {
			mockMvc.perform(put(URL_PREFIX).contentType(contentType).content(json)).andExpect(status().isOk());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
