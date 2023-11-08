package com.foodymoody.be.feed.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 일급 컬렉션
 */
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreMood {

    private static final int MAX_MOOD_COUNT = 3;

    @ElementCollection
    private List<String> moodIds;

    public StoreMood(List<String> moodIds) {
        validate(moodIds);
        this.moodIds = new ArrayList<>(moodIds);
    }

    private void validate(List<String> moodIds) {
        if (moodIds == null || moodIds.size() > MAX_MOOD_COUNT) {
            throw new IllegalArgumentException("Mood는 길이가 " + MAX_MOOD_COUNT + "이하여야 합니다.");
        }
    }

    public List<String> getStoreMoodIds() {
        return Collections.unmodifiableList(moodIds);
    }

}
