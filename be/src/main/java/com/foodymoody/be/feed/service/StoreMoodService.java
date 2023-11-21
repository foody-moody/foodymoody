package com.foodymoody.be.feed.service;

import com.foodymoody.be.common.exception.MoodNotFoundException;
import com.foodymoody.be.feed.domain.StoreMood;
import com.foodymoody.be.feed.domain.StoreMoodId;
import com.foodymoody.be.feed.dto.response.StoreMoodResponse;
import com.foodymoody.be.feed.repository.StoreMoodRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMoodService {

    private final StoreMoodRepository storeMoodRepository;

    public StoreMood findById(StoreMoodId id) {
        return storeMoodRepository.findById(id).orElseThrow(MoodNotFoundException::new);
    }

    public List<StoreMood> findAllById(List<StoreMoodId> ids) {
        return storeMoodRepository.findAllById(ids);
    }

    public List<StoreMoodResponse> findAll() {
        return storeMoodRepository.findAll().stream()
                .map(s -> new StoreMoodResponse(s.getId(), s.getName()))
                .collect(Collectors.toUnmodifiableList());
    }
}
