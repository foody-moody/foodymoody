import { forwardRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { NOTIFICATION_TEXT_TYPE } from 'service/constants/notificationTextMessage';
import { styled } from 'styled-components';
import { UserImage } from 'components/common/userImage/UserImage';
import { FollowListButton } from 'components/follow/followButton/FollowListButton';
import { formatTimeStamp } from 'utils/formatTimeStamp';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';
import { PATH } from 'constants/path';

type Props = {
  notification: NotificationItem;
  onClick: (notificationId: string) => void;
};

export const NotiItem = forwardRef<HTMLLIElement, Props>(
  ({ notification, onClick }, ref) => {
    const navigate = useNavigate();

    const {
      id,
      nickname,
      imageUrl: senderImageUrl = undefined,
    } = notification.sender;

    const { notificationId, type, read } = notification;

    const generateNotiTextByType = (type: NotificationType) => {
      return NOTIFICATION_TEXT_TYPE[type] || '님에게 알림이 왔어요';
    };

    const isFollowNoti = type === 'MEMBER_FOLLOWED_EVENT';

    const hasCommentMessage =
      type === 'FEED_COMMENT_ADDED_EVENT' ||
      type === 'FEED_COMMENT_REPLY_ADDED_EVENT' ||
      type === 'FEED_COLLECTION_COMMENT_ADDED_EVENT' ||
      type === 'FEED_COLLECTION_COMMENT_REPLY_ADDED_EVENT';

    const handleClick = () => {
      if (read) return;
      onClick(notificationId);
    };

    const handleNavigateProfile = () => {
      navigate(PATH.PROFILE + '/' + id);
      sessionStorage.setItem('profileId', id);
    };

    const handleNavigateTarget = () => {
      if (notification.target.feedId) {
        navigate('detail/feed/' + notification.target.feedId, {
          state: { background: 'notiDetailFeed' },
        });
      }
      if (notification.target.feedCollectionId) {
        navigate('/collection/' + notification.target.feedCollectionId);
      }
    };

    return (
      <Wrapper ref={ref} $isRead={read} type={type} onClick={handleClick}>
        <NotiInfo>
          <UserImage
            size="m"
            imageUrl={senderImageUrl || generateDefaultUserImage(id)}
            onClick={handleNavigateProfile}
          />

          <Content onClick={handleNavigateTarget}>
            <div>
              <NotiText>
                <span>{nickname}</span>
                {generateNotiTextByType(type)}
              </NotiText>
              {hasCommentMessage && (
                <>
                  <Message>{notification.target.feedCommentContent}</Message>
                  <Message>{notification.target.feedReplyContent}</Message>
                </>
              )}
              <Time>{formatTimeStamp(notification.createdAt)}</Time>
            </div>

            {notification.target.feedThumbnailUrl && (
              <TargetFeedImg
                src={notification.target.feedThumbnailUrl}
                alt="피드 썸네일 이미지"
              />
            )}
            {notification.target.feedCollectionThumbnailUrl && (
              <TargetFeedImg
                src={notification.target.feedCollectionThumbnailUrl}
                alt="컬렉션 썸네일 이미지"
              />
            )}
          </Content>
        </NotiInfo>

        {/* TODO. 팔로우 제대로 되는지 확인해야함 */}
        {isFollowNoti && notification.target.followed !== undefined && (
          <FollowListButton
            size="xs"
            width={100}
            memberId={notification.sender.id}
            isFollowing={notification.target.followed}
          />
        )}
      </Wrapper>
    );
  }
);

const Wrapper = styled.li<{
  $isRead: boolean;
  type: string;
}>`
  padding: 0 16px;
  display: flex;
  justify-content: space-between;
  width: 100%;
  opacity: ${({ $isRead }) => ($isRead ? 0.5 : 1)};
`;

const NotiInfo = styled.div`
  display: flex;
  gap: 16px;
  width: 100%;
`;

const Content = styled.div`
  display: flex;
  width: 100%;
  justify-content: space-between;
`;
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
  aspect-ratio: 1/1;
  border: 0.5px solid ${({ theme: { colors } }) => colors.black};
  border-radius: 4px;
  margin-left: 8px;
`;
