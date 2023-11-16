package com.foodymoody.be.notification.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.notification.util.NotificationFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NotificationTest {

    @DisplayName("알람의 소유자인지 확인한다.")
    @Test
    void isSameMember() {
        // given
        var notification = NotificationFixture.notification();

        // when
        var sameMember = notification.isSameMember(NotificationFixture.MEMBER_ID);
        var notSameMember = notification.isSameMember(NotificationFixture.NOT_EXIST_MEMBER_ID);

        // then
        assertThat(sameMember).isTrue();
        assertThat(notSameMember).isFalse();
    }

    @DisplayName("알람의 상태를 변경한다.")
    @ParameterizedTest
    @CsvSource({"true", "false"})
    void changeStatus(boolean isRead) {
        // given
        var notification = NotificationFixture.notification();

        // when
        notification.changeStatus(isRead, NotificationFixture.MEMBER_ID, NotificationFixture.UPDATE_AT);

        // then
        assertThat(notification.isRead()).isEqualTo(isRead);
    }

    @DisplayName("알람의 상태 변경시 해당 알람의 소유주가 아니면 예외가 발생한다.")
    @Test
    void changeStatusWithNotSameMember() {
        // given
        var notification = NotificationFixture.notification();

        // when,then
        Assertions.assertThatThrownBy(
                        () -> notification.changeStatus(true, NotificationFixture.NOT_EXIST_MEMBER_ID,
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
        notification.delete(NotificationFixture.MEMBER_ID, NotificationFixture.UPDATE_AT);

        // then
        assertThat(notification.isDeleted()).isTrue();
    }

    @DisplayName("알람을 삭제시 해당 알람의 소유자가 아니면 예외를 발생한다.")
    @Test
    void deleteWithNotSameMember() {
        // given
        var notification = NotificationFixture.notification();

        // when,then
        Assertions.assertThatThrownBy(
                        () -> notification.delete(NotificationFixture.NOT_EXIST_MEMBER_ID, NotificationFixture.UPDATE_AT))
                .isInstanceOf(IllegalArgumentException.class)
                .message().isEqualTo("해당 알림을 수정할 수 없습니다.");
    }
}
