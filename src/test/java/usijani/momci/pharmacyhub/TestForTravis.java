package usijani.momci.pharmacyhub;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import pharmacyhub.PharmacyhubApplication;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PharmacyhubApplication.class)
public class TestForTravis {

	@Test
	void test() {
		assertTrue(true);
	}

}
