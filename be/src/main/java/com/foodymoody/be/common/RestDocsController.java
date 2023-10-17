package com.foodymoody.be.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestDocsController {

    @GetMapping("/api/docs")
    public String getDocs() {
        return "index.html";
    }
}
