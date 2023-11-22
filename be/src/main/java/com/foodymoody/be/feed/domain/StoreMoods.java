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
public class StoreMoods {

    private static final int MAX_MOOD_COUNT = 3;

    @ElementCollection
    private List<String> storeMoodIds;

    public StoreMoods(List<String> storeMoodIds) {
        validate(storeMoodIds);
        this.storeMoodIds = new ArrayList<>(storeMoodIds);
    }

    private void validate(List<String> moodIds) {
        if (moodIds == null || moodIds.size() > MAX_MOOD_COUNT) {
            throw new IllegalArgumentException("Mood는 길이가 " + MAX_MOOD_COUNT + "이하여야 합니다.");
        }
    }

    public List<String> getStoreMoodIds() {
        return Collections.unmodifiableList(storeMoodIds);
    }

}
