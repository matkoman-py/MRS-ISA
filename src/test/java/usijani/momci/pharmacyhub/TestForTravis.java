package usijani.momci.pharmacyhub;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pharmacyhub.PharmacyhubApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PharmacyhubApplication.class })
public class TestForTravis {

	@Test
	void test() {
		assertTrue(true);
	}

}
