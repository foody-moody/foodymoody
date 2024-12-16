package com.foodymoody.be.notification_setting.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.notification_setting.domain.NotificationSettingSummary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotificationSettingReadUseCaseMapperTest {

    @DisplayName("알림 summary를 알림 응답으로 변환한다")
    @Test
    void toResponse() {
        // given
        var summary = new NotificationSettingSummaryImpl(
                true,
                true,
                true,
                true,
                true,
                true
        );

        // when
        var response = NotificationSettingReadUseCaseMapper.toResponse(summary);

        // then
        Assertions.assertAll(
                () -> assertThat(response.isAllNotification()).isTrue(),
                () -> assertThat(response.isFeedLike()).isTrue(),
                () -> assertThat(response.isCollectionLike()).isTrue(),
                () -> assertThat(response.isCommentLike()).isTrue(),
                () -> assertThat(response.isFollow()).isTrue(),
                () -> assertThat(response.isFeedComment()).isTrue(),
                () -> assertThat(response.isCollectionComment()).isTrue()
        );
    }

    @DisplayName("알림 summary가 모든 알림을 허용하면 true를 반환한다")
    @Test
    void isAllNotification() {
        // given
        var summary = new NotificationSettingSummaryImpl(
                true,
                true,
                true,
                true,
                true,
                true
        );

        // when
        var result = NotificationSettingReadUseCaseMapper.isAllNotification(summary);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("알림 summary가 모든 알림을 허용하지 않으면 false를 반환한다")
    @Test
    void isAllNotification_false() {
        // given
        var summary = new NotificationSettingSummaryImpl(
                true,
                true,
                true,
                false,
                true,
                true
        );

        // when
        var result = NotificationSettingReadUseCaseMapper.isAllNotification(summary);

        // then
        assertThat(result).isFalse();
    }

    class NotificationSettingSummaryImpl implements NotificationSettingSummary {

        private boolean feedLike;
        private boolean collectionLike;
        private boolean commentLike;
        private boolean follow;
        private boolean feedComment;
        private boolean collectionComment;

        public NotificationSettingSummaryImpl(
                boolean feedLike,
                boolean collectionLike,
                boolean commentLike,
                boolean follow,
                boolean feedComment,
                boolean collectionComment
        ) {
            this.feedLike = feedLike;
            this.collectionLike = collectionLike;
            this.commentLike = commentLike;
            this.follow = follow;
            this.feedComment = feedComment;
            this.collectionComment = collectionComment;
        }

        @Override
        public boolean isFeedLike() {
            return feedLike;
        }

        @Override
        public boolean isCollectionLike() {
            return collectionLike;
        }

        @Override
        public boolean isCommentLike() {
            return commentLike;
        }

        @Override
        public boolean isFollow() {
            return follow;
        }

        @Override
        public boolean isFeedComment() {
            return feedComment;
        }

        @Override
        public boolean isCollectionComment() {
            return collectionComment;
        }

    }

}
