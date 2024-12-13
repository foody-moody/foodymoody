package com.foodymoody.be.auth.infra.util;

import java.util.Random;

public class UniqueNicknameGenerator {

    private static final String[] ADJECTIVES = {"기운찬", "씩씩한", "멋진", "예쁜", "빛나는", "귀여운", "대담한", "신비한", "상쾌한", "행복한"};
    private static final String[] NOUNS = {"호랑이", "독수리", "고양이", "강아지", "코끼리", "돌고래", "해바라기", "별", "우주", "바다"};

    public static String generate() {
        Random random = new Random();
        String adjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
        String noun = NOUNS[random.nextInt(NOUNS.length)];
        int number = random.nextInt(1000);
        return adjective + noun + number;
    }

}
