package com.foodymoody.be.mood.service;

import com.foodymoody.be.mood.util.MoodMapper;
import com.foodymoody.be.mood.controller.dto.MoodResponse;
import com.foodymoody.be.mood.controller.dto.RandomMoodResponse;
import com.foodymoody.be.mood.domain.Mood;
import com.foodymoody.be.mood.repository.MoodRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    public RandomMoodResponse findRandom(int count) {
        List<Mood> moods = moodRepository.findRandom(count);
        List<MoodResponse> moodResponses = moods.stream()
                .map(MoodMapper::toResponse)
                .collect(Collectors.toUnmodifiableList());
        return MoodMapper.toRandomResponse(moodResponses);
    }

    public Slice<MoodResponse> findSlice(Pageable pageable) {
        //    TODO 정렬 조건 필요
        Slice<Mood> slice = moodRepository.findAll(pageable);
        return MoodMapper.toSliceResponse(slice);
    }
}
