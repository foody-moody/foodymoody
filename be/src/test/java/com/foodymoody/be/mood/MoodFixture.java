package com.foodymoody.be.mood;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.TasteMoodId;
import com.foodymoody.be.member.domain.TasteMood;

public enum MoodFixture {

    TASTE_MOOD_1("1", "베지테리언1"),
    TASTE_MOOD_2("2", "베지테리언2"),
    TASTE_MOOD_3("3", "무드1"),
    TASTE_MOOD_4("4", "무드2"),
    TASTE_MOOD_5("5", "무드3"),
    TASTE_MOOD_6("6", "무드4"),
    STORE_MOOD_1("1", "가족과 함께"),
    STORE_MOOD_2("2", "혼밥"),
    STORE_MOOD_3("3", "감성"),
    STORE_MOOD_4("4", "데이트"),
    STORE_MOOD_5("5", "루프탑"),
    STORE_MOOD_6("6", "특별한 테마");

    private String id;
    private String name;

    MoodFixture(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TasteMood getTasteMood() {
        TasteMoodId tasteMoodId = IdFactory.createTasteMoodId(id);
        return new TasteMood(tasteMoodId, name);
    }
}
