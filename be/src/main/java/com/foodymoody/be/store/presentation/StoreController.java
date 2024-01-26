package com.foodymoody.be.store.presentation;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.service.StoreReadService;
import com.foodymoody.be.store.application.service.dto.response.StoreDetailsResponse;
import com.foodymoody.be.store.application.service.dto.response.StoreSearchResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreReadService storeReadService;

    @GetMapping("/{id}")
    public ResponseEntity<StoreDetailsResponse> fetchDetails(
            @PathVariable StoreId id
    ) {
        StoreDetailsResponse response = storeReadService.fetchDetails(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<StoreSearchResponse>> search(
            @RequestParam("query") String query
    ) {
        List<StoreSearchResponse> response = storeReadService.search(query);
        return ResponseEntity.ok(response);
    }

}
