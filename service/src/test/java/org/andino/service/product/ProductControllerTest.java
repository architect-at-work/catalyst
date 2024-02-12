package org.andino.service.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.andino.service.product.dto.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc()
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void returnHello() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/product/hello"));

		resultActions.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(new Greeting("Hello"))));
	}

}

// another way of writing test