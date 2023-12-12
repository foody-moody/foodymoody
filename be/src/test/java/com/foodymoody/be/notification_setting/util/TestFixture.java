package com.foodymoody.be.notification_setting.util;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.member.domain.Member;

public class TestFixture {

    public static Member memberAlbert() {
        return Member.of("1", "albert@albert.com", "albert", "albert123!", "albert123!", IdFactory.createTasteMoodId("1"));
    }
}
