package com.foodymoody.be;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {

    @PostMapping("/api/feeds")
    public ResponseEntity<Void> registerFeed() {
        return ResponseEntity.ok().build();
    }
}
