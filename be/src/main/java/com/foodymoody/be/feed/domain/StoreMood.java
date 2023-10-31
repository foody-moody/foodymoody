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

    // TODO: moodId로 관리하도록 리팩토링
    public StoreMood(List<String> moods) {
        validate(moods);
        this.moodIds = new ArrayList<>(moods);
    }

    private void validate(List<String> moods) {
        if (moods == null || moods.size() > MAX_MOOD_COUNT) {
            throw new IllegalArgumentException("StoreMood는 길이가 3 이하여야 합니다.");
        }
    }

    public List<String> getStoreMoodIds() {
        return Collections.unmodifiableList(moodIds);
    }

}
