package com.foodymoody.be.auth;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/auth/login")
    public Map<String, String> login() {
        return Map.of("accessToken", "dfsfsa-fsdafs-fsdfsa", "refreshToken", "fdasfsdfs-fsdfsad-fdsfas");
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PostMapping("/auth/logout")
    public void logout() {
    }
}
