package com.foodymoody.be.member.controller.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class MemberProfileResponse {

    private String memberId;
    private String imageUrl;
    private String nickname;
    private String email;
    private String tasteMood;
//    TODO 피드 미리보기 조회 구현 후 추가
    List<MemberProfileFeedPreviewResponse> myFeeds;

    private MemberProfileResponse(String memberId, String imageUrl, String nickname, String email, String mood,
            List<MemberProfileFeedPreviewResponse> myFeeds) {
        this.memberId = memberId;
        this.imageUrl = imageUrl;
        this.nickname = nickname;
        this.email = email;
        this.tasteMood = mood;
        this.myFeeds = myFeeds;
    }

    public static MemberProfileResponse of(String id, String imageUrl, String nickname, String email, String mood, List<MemberProfileFeedPreviewResponse> feedPreviews) {
        return new MemberProfileResponse(id, imageUrl, nickname, email, mood, feedPreviews);
    }
}