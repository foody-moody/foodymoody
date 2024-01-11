import { forwardRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { Button } from 'components/common/button/Button';
import { UserImage } from 'components/common/userImage/UserImage';
import { formatTimeStamp } from 'utils/formatTimeStamp';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';
import { useModal } from './useModal';
import { PATH } from 'constants/path';

type Props = {
  notification: NotificationItem;
  onClick: (notificationId: string) => void;
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
  ({ notification, onClick }, ref) => {
    const navigate = useNavigate();

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
    const isFollowNoti = type === 'MEMBER_FOLLOWED_EVENT';

    const handleClick = () => {
      if (read) return;
      onClick(notificationId);
    };

    const handleNavigateProfile = () => {
      // api에서 지금 sender id를 제대로 안주고 있음
      navigate(PATH.PROFILE + '/' + id);
    };

    const handleNavigateTarget = () => {
      // TODO.컬렉션일 경우도 나중에 처리해야함
      navigate('detail/feed/' + feedId, {
        state: { background: 'notiDetailFeed' },
      });
    };

    return (
      <Wrapper ref={ref} $isRead={read} type={type} onClick={handleClick}>
        <NotiInfo>
          <UserImage
            size="m"
            imageUrl={senderImageUrl || generateDefaultUserImage(id)}
            onClick={handleNavigateProfile}
          />

          {/* TODO 3. Content 영역 클릭 시 해당 글 모달 키기 */}
          <Content onClick={handleNavigateTarget}>
            <div>
              <NotiText>
                <span>{nickname}</span>
                {notiText}
              </NotiText>
              {!isFollowNoti && <Message>{commentMessage}</Message>}
              <Time>{formatTimeStamp(notification.createdAt)}</Time>
            </div>

            {/* TODO. UsrImage 컴포넌트 수정되면 바꾸기 */}
            {!isFollowNoti && (
              <TargetFeedImg src={targetImageUrl} alt="피드 썸네일 이미지" />
            )}
          </Content>
        </NotiInfo>

        {/* TODO 4. 팔로우 버튼 누르면 팔로우 요청 보내기. 
            팔로우 된 경우에 언팔 버튼을 띄울지? 아니면 그냥 팔로우됨. 으로 띄울지 이야기 해부기 */}
        {isFollowNoti && (
          <FollowBtn backgroundColor="black" size="xs">
            팔로우
          </FollowBtn>
        )}
      </Wrapper>
    );
  }
);

const FollowBtn = styled(Button)`
  width: 100px;
`;

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

const Thumbnail = styled.img`
  cursor: pointer;
  width: 60px;
  height: 60px;
  border: 0.5px solid ${({ theme: { colors } }) => colors.black};
  border-radius: ${({ theme: { radius } }) => radius.half};
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