package com.foodymoody.be.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestDocsController {

    @GetMapping("/api/docs")
    public String getDocs() {
        return "/docs/index.html";
    }
}
