package usijani.momci.pharmacyhub;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;
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
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PharmacyhubApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {
	
	private static final String URL_PREFIX = "";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testSuppliersAndAdmins()  {
		try {
			mockMvc.perform(get(URL_PREFIX+"/suppliers-and-admins")).andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$.[*].id").value(hasItem("4f3bgfza-fda8-4a50-aa05-be2be4fb2884")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveUser() {
		Patient k = new Patient();
		k.setName("zezanje");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(k);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		try {
			this.mockMvc.perform(post(URL_PREFIX+"/register").contentType(contentType).content(json)).andExpect(status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
