package kr.or.cola.backend.common.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("COLA server is running");
    }
}