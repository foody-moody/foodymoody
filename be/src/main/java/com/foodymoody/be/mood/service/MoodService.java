package com.foodymoody.be.mood.service;

import com.foodymoody.be.common.exception.DuplicateMoodException;
import com.foodymoody.be.mood.controller.dto.MoodRegisterRequest;
import com.foodymoody.be.mood.controller.dto.MoodRegisterResponse;
import com.foodymoody.be.mood.util.MoodMapper;
import com.foodymoody.be.mood.controller.dto.MoodResponse;
import com.foodymoody.be.mood.controller.dto.RandomMoodResponse;
import com.foodymoody.be.mood.domain.Mood;
import com.foodymoody.be.mood.repository.MoodRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    public MoodResponse fetchById(String id) {
        return MoodMapper.toResponse(findMoodById(id));
    }

    public RandomMoodResponse fetchRandom(int count) {
        List<Mood> moods = moodRepository.findRandom(count);
        return MoodMapper.toRandomResponse(moods);
    }

    public Slice<MoodResponse> fetchSlice(Pageable pageable) {
        //    TODO 정렬 조건 필요
        Slice<Mood> slice = moodRepository.findAll(pageable);
        return MoodMapper.toSliceResponse(slice);
    }

    @Transactional
    public MoodRegisterResponse create(MoodRegisterRequest request) {
        String moodName = request.getMood();
        if (Objects.isNull(moodName)) {
            throw new IllegalArgumentException("무드를 입력해주세요");
        }
        validateNameExits(moodName);
        Mood saved = moodRepository.save(MoodMapper.toEntity(moodName));
        return MoodMapper.toRegisterResponse(saved);
    }

    public void validateNameExits(String name) {
        if (moodRepository.existsByName(name)) {
            throw new DuplicateMoodException();
        }
    }
}
