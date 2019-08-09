package ru.popovich.emergencyassist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.popovich.emergencyassist.model.Organization;
//import ru.popovich.emergencyassist.model.Organization;

//import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmergencyassistApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void noAuth(){
		String orgId = "1";
		String request = "/api/v1/organization/" + orgId;

		ResponseEntity<Organization> organizationResponseEntity =
				testRestTemplate.getForEntity(request, Organization.class);

//		assertEquals(HttpStatus.OK, organizationResponseEntity.getStatusCode());
	}

}
