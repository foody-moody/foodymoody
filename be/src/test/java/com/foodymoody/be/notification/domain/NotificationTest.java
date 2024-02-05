package com.foodymoody.be.notification.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification.util.NotificationFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("알람 도메인")
class NotificationTest {

    @DisplayName("알람의 상태를 변경한다.")
    @ParameterizedTest
    @CsvSource({"true", "false"})
    void changeStatus(boolean isRead) {
        // given
        var notification = NotificationFixture.notification();

        // when
        notification.changeStatus(isRead, NotificationFixture.notification().getToMemberId(),
                NotificationFixture.UPDATE_AT);

        // then
        assertThat(notification.isRead()).isEqualTo(isRead);
    }

    @DisplayName("알람의 상태 변경시 해당 알람의 소유주가 아니면 예외가 발생한다.")
    @Test
    void changeStatusWithNotSameMember() {
        // given
        var notification = NotificationFixture.notification();
        MemberId notExistMemberId = NotificationFixture.getNotExistMemberId();

        // when,then
        Assertions.assertThatThrownBy(
                        () -> notification.changeStatus(true, notExistMemberId,
                                NotificationFixture.UPDATE_AT))
                .isInstanceOf(IllegalArgumentException.class)
                .message().isEqualTo("해당 알림을 수정할 수 없습니다.");
    }


    @DisplayName("알람을 삭제한다.")
    @Test
    void delete() {
        // given
        var notification = NotificationFixture.notification();

        // when
        notification.delete(NotificationFixture.getToMemberId(), NotificationFixture.UPDATE_AT);

        // then
        assertThat(notification.isDeleted()).isTrue();
    }

    @DisplayName("알람을 삭제시 해당 알람의 소유자가 아니면 예외를 발생한다.")
    @Test
    void deleteWithNotSameMember() {
        // given
        var notification = NotificationFixture.notification();
        MemberId notExistMemberId = NotificationFixture.getNotExistMemberId();

        // when,then
        Assertions.assertThatThrownBy(
                        () -> notification.delete(notExistMemberId, NotificationFixture.UPDATE_AT))
                .isInstanceOf(IllegalArgumentException.class)
                .message().isEqualTo("해당 알림을 수정할 수 없습니다.");
    }
}
