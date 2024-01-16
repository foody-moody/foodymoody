package com.foodymoody.be.feed_collection.infra.usecase;

import com.foodymoody.be.feed.application.dto.response.StoreMoodResponse;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.feed_collection.domain.AuthorSummaryResponse;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import com.foodymoody.be.feed_collection.domain.FeedSummaryResponse;
import com.foodymoody.be.feed_collection.infra.usecase.dto.FeedCollectionCommentResponse;
import com.foodymoody.be.feed_collection.infra.usecase.dto.FeedCollectionMoodResponse;
import com.foodymoody.be.feed_collection.infra.usecase.dto.FeedCollectionResponse;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentSummary;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.TasteMood;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Slice;

public class FeedCollectionMapper {

    private FeedCollectionMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static FeedCollectionResponse toFeedCollectionResponse(
            FeedCollectionSummary summary, List<FeedCollectionMoodResponse> moodResponses
    ) {
        return new FeedCollectionResponse(
                summary.getId(),
                summary.getAuthor(),
                summary.getTitle(),
                summary.getDescription(),
                summary.getThumbnailUrl(),
                summary.getLikeCount(),
                summary.isLiked(),
                summary.getFollowerCount(),
                summary.getCommentCount(),
                summary.getFeedCount(),
                moodResponses,
                summary.getCreatedAt(),
                summary.getUpdatedAt()
        );
    }

    public static AuthorSummaryResponse toAuthorSummaryResponse(
            Member author, Image authorProfileImage, TasteMood authorTasteMood
    ) {
        return new AuthorSummaryResponse(
                author.getId(),
                author.getNickname(),
                authorProfileImage.getUrl(),
                authorTasteMood.getName()
        );
    }

    public static List<FeedSummaryResponse> toFeedSummaryResponse(Slice<Feed> feeds) {
        return feeds.stream()
                .map(feed -> {
                    List<StoreMoodResponse> moods = toStoreMoodResponse(feed.getStoreMoods());
                    return toFeedSummaryResponse(feed, moods, false);
                })
                .collect(Collectors.toList());
    }

    public static List<StoreMoodResponse> toStoreMoodResponse(List<StoreMood> storeMoods) {
        return storeMoods.stream()
                .map(storeMood -> new StoreMoodResponse(
                        storeMood.getId(),
                        storeMood.getName()
                ))
                .collect(Collectors.toList());
    }

    public static FeedSummaryResponse toFeedSummaryResponse(
            Feed feed, List<StoreMoodResponse> moods, boolean isLiked
    ) {
        return new FeedSummaryResponse(
                feed.getId().getValue(),
                feed.getProfileImageUrl(),
                feed.getReview(),
                moods,
                feed.getLikeCount(),
                feed.getCommentCount(),
                isLiked,
                feed.getCreatedAt(),
                feed.getUpdatedAt()
        );
    }

    public static Slice<FeedCollectionCommentResponse> toCommentResponse(
            Slice<FeedCollectionCommentSummary> comments
    ) {
        return comments.map(comment -> new FeedCollectionCommentResponse(
                comment.getId(),
                comment.getFeedId(),
                comment.getContent(),
                comment.isDeleted(),
                comment.isHasReply(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.isLiked(),
                comment.getLikeCount(),
                comment.getMemberId(),
                comment.getNickname(),
                comment.getProfileImageUrl(),
                comment.getMood()
        ));
    }
}