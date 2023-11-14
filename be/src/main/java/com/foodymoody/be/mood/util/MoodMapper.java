package com.foodymoody.be.mood.util;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.mood.controller.dto.MoodRegisterResponse;
import com.foodymoody.be.mood.controller.dto.MoodResponse;
import com.foodymoody.be.mood.controller.dto.RandomMoodResponse;
import com.foodymoody.be.mood.domain.Mood;
import java.util.List;
import org.springframework.data.domain.Slice;

public class MoodMapper {

    private MoodMapper() {
        throw new IllegalArgumentException(UTILITY_CLASS);
    }

    public static MoodResponse toResponse(Mood mood) {
        return new MoodResponse(mood.getId(), mood.getName());
    }

    public static RandomMoodResponse toRandomResponse(List<MoodResponse> moodResponses) {
        return new RandomMoodResponse(moodResponses);
    }

    public static Slice<MoodResponse> toSliceResponse(Slice<Mood> slice) {
        return slice.map(MoodMapper::toResponse);
    }

    public static Mood toEntity(String mood) {
        return new Mood(IdGenerator.generate(), mood);
    }

    public static MoodRegisterResponse toRegisterResponse(Mood saved) {
        return new MoodRegisterResponse(saved.getId(), saved.getName());
    }
}
