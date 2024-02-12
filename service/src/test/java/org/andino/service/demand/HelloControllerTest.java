package org.andino.service.demand;

import org.andino.service.demand.dto.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

class HelloControllerTest {

	@Test
	public void returnHello() {
		HelloController helloController = new HelloController();

		ResponseEntity<Greeting> response = helloController.greeting();

		assertThat(response.getStatusCode()).isEqualTo(OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getText()).isEqualTo("Hello from demand!");
	}

}