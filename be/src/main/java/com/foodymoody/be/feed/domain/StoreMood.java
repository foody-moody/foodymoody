package com.foodymoody.be.feed.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

/**
 * 일급 컬렉션
 */
@Embeddable
public class StoreMood {

    private static final int MAX_MOOD_COUNT = 3;

    @ElementCollection
    private List<String> moods;

    public StoreMood() {
    }

    public StoreMood(List<String> moods) {
        validate(moods);
        this.moods = new ArrayList<>(moods);
    }

    private void validate(List<String> moods) {
        if (moods == null || moods.size() > MAX_MOOD_COUNT) {
            throw new IllegalArgumentException("StoreMood는 길이가 3 이하여야 합니다.");
        }
    }

    public List<String> getStoreMood() {
        return Collections.unmodifiableList(moods);
    }

}
