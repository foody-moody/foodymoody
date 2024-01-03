package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.TasteMoodId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemberCreatedEvent implements Event {

    private MemberId memberid;
    private String email;
    private String nickname;
    private ImageId profileImageId;
    private TasteMoodId tasteMoodId;
    private LocalDateTime createdAt;

    private MemberCreatedEvent(MemberId memberid, String email, String nickname, ImageId profileImageId,
            TasteMoodId tasteMoodId, LocalDateTime createdAt) {
        this.memberid = memberid;
        this.email = email;
        this.nickname = nickname;
        this.profileImageId = profileImageId;
        this.tasteMoodId = tasteMoodId;
        this.createdAt = createdAt;
    }

    public static MemberCreatedEvent of(MemberId id, String email, String nickname, ImageId profileImageId,
            TasteMoodId tasteMoodId, LocalDateTime createdAt) {
        return new MemberCreatedEvent(id, email, nickname, profileImageId, tasteMoodId, createdAt);
    }
}
