package org.andino.service.demand;

import org.andino.service.demand.models.Greeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController implements  DemandApi {
    @Override
    public ResponseEntity<Greeting> greeting() {
        return ResponseEntity.ok(new Greeting("Hello from demand!"));
    }
}
