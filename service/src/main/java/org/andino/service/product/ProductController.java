package org.andino.service.product;

import org.andino.service.product.api.ProductApi;
import org.andino.service.product.dto.Greeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements ProductApi {

	@Override
	public ResponseEntity<Greeting> hello() {
		return ResponseEntity.ok(new Greeting("Hello"));
	}

}
