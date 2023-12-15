import { forwardRef } from 'react';
import { styled } from 'styled-components';
import { formatTimeStamp } from 'utils/formatTimeStamp';

type Props = {
  notification: NotificationItem;
};

// 분리하기
const notificationMessages = {
  FEED_LIKED_ADDED_EVENT: '님이 회원님의 피드를 좋아합니다.',
  COMMENT_LIKED_ADDED_EVENT: '님이 회원님의 댓글을 좋아합니다.',
  FEED_COLLECTION_LIKED_ADDED_EVENT: '님이 회원님의 컬렉션을 좋아합니다.',
  FEED_COMMENT_ADDED_EVENT: '님이 회원님의 피드에 댓글을 남겼습니다.',
  COLLECTION_COMMENT_ADDED_EVENT: '님이 회원님의 컬렉션에 댓글을 남겼습니다.',
  MEMBER_MENTIONED_EVENT: '님이 댓글에서 회원님을 언급 했습니다.',
  MEMBER_FOLLOWED_EVENT: '님이 회원님을 팔로우 했습니다.',
};

function generateNotiText(type: NotificationType) {
  return notificationMessages[type] || '님에게 알림이 왔어요';
}

export const NotiItem = forwardRef<HTMLLIElement, Props>(
  ({ notification }, ref) => {
    const {
      id,
      nickname,
      imageUrl: senderImageUrl = undefined,
    } = notification.sender;
    const {
      feedId,
      imageUrl: targetImageUrl,
      commentId,
      commentMessage = null,
    } = notification.target || {};
    const { notificationId, type, createdAt, updatedAt, read } = notification;

    const notiText = generateNotiText(type);

    return (
      <Wrapper ref={ref} $isRead={read}>
        <Left>
          <Thumbnail src={senderImageUrl} alt="유저 프로필 사진" />
          <Content>
            <NotiText>
              <span>{nickname}</span>
              {notiText}
            </NotiText>
            {type !== 'MEMBER_FOLLOWED_EVENT' && (
              <Message>{commentMessage}</Message>
            )}
            <Time>{formatTimeStamp(notification.createdAt)}</Time>
          </Content>
        </Left>
        {type !== 'MEMBER_FOLLOWED_EVENT' && (
          <TargetFeedImg src={targetImageUrl} alt="피드 썸네일 이미지" />
        )}
        {type == 'MEMBER_FOLLOWED_EVENT' && <button>팔로우</button>}
      </Wrapper>
    );
  }
);

const Wrapper = styled.li<{
  $isRead: boolean;
}>`
  padding: 0 16px;
  /* max-width: 530px; */
  display: flex;
  justify-content: space-between;
  width: 100%;
  opacity: ${({ $isRead }) => ($isRead ? 0.5 : 1)};
`;

const Left = styled.div`
  display: flex;
  gap: 16px;
  /* align-items: center; */
`;

const Thumbnail = styled.img`
  /* margin-top: 4px; */
  width: 60px;
  height: 60px;
  border: 0.5px solid ${({ theme: { colors } }) => colors.black};
  border-radius: ${({ theme: { radius } }) => radius.half};
`;

const Content = styled.div``;
const NotiText = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};

  span {
    font: ${({ theme: { fonts } }) => fonts.displayB14};
  }
`;

const Message = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;

const Time = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;

const TargetFeedImg = styled.img`
  width: 60px;
  height: 60px;
  aspect-ratio: 1;
  border: 0.5px solid ${({ theme: { colors } }) => colors.black};
  border-radius: 4px;
  margin-left: 8px;
`;
