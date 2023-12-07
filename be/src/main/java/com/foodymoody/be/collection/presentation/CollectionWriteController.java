package com.foodymoody.be.collection.presentation;

import com.foodymoody.be.collection.infra.usecase.CollectionWriteUseCase;
import com.foodymoody.be.common.annotation.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CollectionWriteController {

    private final CollectionWriteUseCase useCase;

    @PostMapping("/api/collections")
    public ResponseEntity<Void> createCollection(@RequestBody CollectionCreateRequest request,
            @MemberId String memberId) {
        useCase.createCollection(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
