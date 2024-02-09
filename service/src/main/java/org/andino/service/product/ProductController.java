package org.andino.service.product;

import org.andino.service.product.models.Hello;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements HelloApi {
    @Override
    public ResponseEntity<Hello> hello() {
        return ResponseEntity.ok(new Hello("Hello"));
    }
}
