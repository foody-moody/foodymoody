package com.foodymoody.be;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DocumentController {

    @PostMapping("/members")
    public ResponseEntity<Map<String, Object>> signup() {
        return ResponseEntity.ok().body(Map.of("id", 1L));
    }

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

    @GetMapping("/members/{memberId}")
    public ResponseEntity<Map<String, Object>> loadProfile(@PathVariable Long memberId) {
        return ResponseEntity.ok().body(
                Map.of(
                        "memberId", 3L,
                        "myImageUrl", "https://www.image.com",
                        "nickname", "보노",
                        "email", "bono@bono.com",
                        "mood", "베지테리안",
                        "myFeeds", List.of(
                                Map.of(
                                        "feedId", 1L,
                                        "createdAt", "2023-10-17T16:54:03",
                                        "location", "맛잇게 매운 콩불 범계점",
                                        "review", "맛있게 먹었습니다.",
                                        "mood", "베지테리안",
                                        "images", List.of(
                                                Map.of(
                                                        "imageUrl", "https://www.googles.com",
                                                        "menu", Map.of(
                                                                "name", "마라탕",
                                                                "numStar", 4)
                                                ),
                                                Map.of(
                                                        "imageUrl", "https://www.googles.com",
                                                        "menu", Map.of(
                                                                "name", "감자탕",
                                                                "numStar", 3
                                                        )
                                                )
                                        ),
                                        "likeCount", 17,
                                        "commentCount", 3
                                ),
                                Map.of(
                                        "feedId", 1L,
                                        "createdAt", "2023-10-17T16:54:03",
                                        "location", "맛잇게 매운 콩불 범계점",
                                        "review", "맛있게 먹었습니다.",
                                        "mood", "베지테리안",
                                        "images", List.of(
                                                Map.of(
                                                        "imageUrl", "https://www.googles.com",
                                                        "menu", Map.of(
                                                                "name", "마라탕",
                                                                "numStar", 4)
                                                ),
                                                Map.of(
                                                        "imageUrl", "https://www.googles.com",
                                                        "menu", Map.of(
                                                                "name", "감자탕",
                                                                "numStar", 3
                                                        )
                                                )
                                        ),
                                        "likeCount", 17,
                                        "commentCount", 3
                                )
                        ),
                        "myFeedsCount", 2
                )
        );
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
