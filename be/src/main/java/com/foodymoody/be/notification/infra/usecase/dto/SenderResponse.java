package com.foodymoody.be.notification.infra.usecase.dto;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.Getter;

/**
 * The SenderResponse class represents a response containing information about the sender of a notification.
 */
@Getter
public class SenderResponse {

    /**
     * The ID of the sender.
     */
    private final MemberId id;
    /**
     * The nickname of the sender.
     */
    private final String nickname;
    /**
     * The profile image URL of the sender.
     */
    private final String imageUrl;

    private SenderResponse(MemberId id, String nickname, String imageUrl) {
        this.id = id;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }

    /**
     * Creates a new SenderResponse object.
     *
     * @param id       The ID of the sender.
     * @param nickname The nickname of the sender.
     * @param imageUrl The profile image URL of the sender.
     * @return The created SenderResponse object.
     */
    public static SenderResponse of(MemberId id, String nickname, String imageUrl) {
        return new SenderResponse(id, nickname, imageUrl);
    }
}
