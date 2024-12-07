package com.foodymoody.be.store_like.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store_like.application.service.StoreLikeWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreLikeWriteController {

    private final StoreLikeWriteService service;

    @PostMapping("/{storeId}/likes")
    public ResponseEntity<Void> like(
            @PathVariable StoreId storeId,
            @CurrentMemberId MemberId memberId
    ) {
        service.register(storeId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{storeId}/likes")
    public ResponseEntity<Void> unlike(
            @PathVariable StoreId storeId,
            @CurrentMemberId MemberId memberId
    ) {
        service.cancel(storeId, memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
