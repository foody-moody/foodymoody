package com.foodymoody.be.mood.service;

import com.foodymoody.be.mood.domain.Mood;
import com.foodymoody.be.mood.repository.MoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MoodService {

    private final MoodRepository moodRepository;

    public Mood findMoodByName(String s) {
        return moodRepository.findByName(s)
                .orElseThrow(() -> new IllegalArgumentException("Mood를 찾을 수 없습니다."));
    }

    public Mood findMoodById(String id) {
        return moodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mood를 찾을 수 없습니다."));
    }

}
