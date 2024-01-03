package com.foodymoody.be.member.application;

import com.foodymoody.be.common.exception.MoodNotFoundException;
import com.foodymoody.be.common.util.ids.TasteMoodId;
import com.foodymoody.be.member.application.dto.response.TasteMoodResponse;
import com.foodymoody.be.member.domain.TasteMood;
import com.foodymoody.be.member.domain.TasteMoodRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TasteMoodReadService {

    private final TasteMoodRepository tasteMoodRepository;

    public TasteMood findById(TasteMoodId id) {
        return tasteMoodRepository.findById(id).orElseThrow(MoodNotFoundException::new);
    }

    public List<TasteMoodResponse> findAll() {
        return tasteMoodRepository.findAll().stream()
                .map(m -> new TasteMoodResponse(m.getId(), m.getName()))
                .collect(Collectors.toUnmodifiableList());
    }
}
