package com.foodymoody.be.feed.application;

import com.foodymoody.be.common.exception.MoodNotFoundException;
import com.foodymoody.be.common.util.ids.StoreMoodId;
import com.foodymoody.be.feed.application.dto.response.StoreMoodResponse;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.feed.domain.repository.StoreMoodRepository;
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

    public List<StoreMoodResponse> fetchAll() {
        return storeMoodRepository.findAll().stream()
                .map(s -> new StoreMoodResponse(s.getId(), s.getName()))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<String> findNamesById(List<StoreMoodId> storeMoodIds) {
        return storeMoodRepository.findAllById(storeMoodIds).stream()
                .map(StoreMood::getName)
                .collect(Collectors.toList());
    }

}
