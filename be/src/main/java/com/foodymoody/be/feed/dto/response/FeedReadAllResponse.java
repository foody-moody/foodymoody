package com.foodymoody.be.feed.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedReadAllResponse {

    private Long id;
    private FeedMemberResponse member;
    private String location;
    // TODO: createdAt, updatedAt 추가 -> 테스트 코드 로직도 변경
    private String review;
    private String mood;
    private List<FeedImageMenuResponse> images;
    private int likeCount;
    private boolean isLiked;
    private int commentCount;

    @Builder
    public FeedReadAllResponse(Long id, FeedMemberResponse member, String location, String review, String mood,
            List<FeedImageMenuResponse> images, int likeCount, boolean isLiked, int commentCount) {
        this.id = id;
        this.member = member;
        this.location = location;
        this.review = review;
        this.mood = mood;
        this.images = images;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
        this.commentCount = commentCount;
    }

    public Long getId() {
        return id;
    }

    public FeedMemberResponse getMember() {
        return member;
    }

    public String getLocation() {
        return location;
    }

    public String getReview() {
        return review;
    }

    public String getMood() {
        return mood;
    }

    public List<FeedImageMenuResponse> getImages() {
        return images;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public boolean isLiked() {
        return isLiked;
    }
}
