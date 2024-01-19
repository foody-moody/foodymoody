package com.foodymoody.be.common.event;

public enum NotificationType {
    /**
     * 피드 추가 이벤트
     */
    FEED_ADDED_EVENT,
    /**
     * 피드 좋아요 추가 이벤트
     */
    FEED_LIKED_ADDED_EVENT,
    /**
     * 피드에 댓글 추가 이벤트
     */
    FEED_COMMENT_ADDED_EVENT,
    /**
     * 피드에 댓글 좋아요 추가 이벤트
     */
    FEED_COMMENT_LIKED_ADDED_EVENT,
    /**
     * 피드 댓글에 답글 추가 이벤트
     */
    FEED_COMMENT_REPLY_ADDED_EVENT,
    /**
     * 피드 댓글에 답글 좋아요 추가 이벤트
     */
    FEED_COMMENT_REPLY_LIKED_ADDED_EVENT,
    /**
     * 피드 컬렉션 추가 이벤트
     */
    FEED_COLLECTION_ADDED_EVENT,
    /**
     * 피드 컬렉션 좋아요 추가 이벤트
     */
    FEED_COLLECTION_LIKED_ADDED_EVENT,
    /**
     * 피드 컬렉션에 댓글 추가 이벤트
     */
    FEED_COLLECTION_COMMENT_ADDED_EVENT,
    /**
     * 피드 컬렉션에 댓글 좋아요 추가 이벤트
     */
    FEED_COLLECTION_COMMENT_LIKED_ADDED_EVENT,
    /**
     * 피드 컬렉션 댓글에 답글 추가 이벤트
     */
    FEED_COLLECTION_COMMENT_REPLY_ADDED_EVENT,
    /**
     * 피드 컬렉션 댓글에 답글 좋아요 추가 이벤트
     */
    FEED_COLLECTION_COMMENT_REPLY_LIKED_ADDED_EVENT,
    /**
     * 유저 멘션 추가 이벤트
     */
    MEMBER_MENTIONED_EVENT,
    /**
     * 유저 팔로우 추가 이벤트
     */
    MEMBER_FOLLOWED_EVENT,
}
