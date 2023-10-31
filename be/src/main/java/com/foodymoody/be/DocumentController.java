package com.foodymoody.be;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DocumentController {

    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<Void> withdraw(@PathVariable Long memberId) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/members/{memberId}")
    public ResponseEntity<Void> updateProfile(@PathVariable Long memberId) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/members/{memberId}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long memberId) {
        return ResponseEntity.noContent().build();
    }

    @PostMapping("auth/login")
    public ResponseEntity<Map<String, Object>> login() {
        return ResponseEntity.ok().body(
                Map.of(
                        "accessToken", "dfsfsa-fsdafs-fsdfsa",
                        "refreshToken", "fdasfsdfs-fsdfsad-fdsfas"
                )
        );
    }
}
