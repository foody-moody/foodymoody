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
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreMoodReadService {

    private final StoreMoodRepository storeMoodRepository;

    public StoreMood findById(StoreMoodId id) {
        return storeMoodRepository.findById(id).orElseThrow(MoodNotFoundException::new);
    }

    public List<StoreMoodResponse> fetchAll() {
        return storeMoodRepository.findAll().stream()
                .map(s -> new StoreMoodResponse(s.getId(), s.getName()))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<StoreMood> fetchAllByStoreMoodIds(List<StoreMoodId> storeMoodIds) {
        return storeMoodRepository.findAllById(storeMoodIds);
    }

}