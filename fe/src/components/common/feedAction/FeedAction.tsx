import { useParams } from 'react-router-dom';
import { useDeleteFeedLike, usePostFeedLike } from 'service/queries/like';
// import { usePostLike } from 'service/queries/like';
import { styled } from 'styled-components';
import { useAuthState } from 'hooks/auth/useAuth';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { ChatDotsIcon, HeartBgIcon, HeartFillIcon } from '../icon/icons';

type Props = {
  feedId: string;
  isLiked?: boolean;
  likeCount?: number;
  commentCount?: number;
  onClickCommentIcon?: () => void;
};

export const FeedAction: React.FC<Props> = ({
  feedId,
  isLiked,
  likeCount = 0,
  commentCount = 0,
  onClickCommentIcon,
}) => {
  const { id: param } = useParams() as { id: string };
  const { navigateToLogin } = usePageNavigator();
  const { isLogin } = useAuthState();
  const { mutate: likeMutate } = usePostFeedLike(param);
  const { mutate: unLikeMutate } = useDeleteFeedLike(param);

  const LikeIcon = isLiked ? HeartFillIcon : HeartBgIcon;

  const handleSubmitLike = () => {
    if (isLogin) {
      isLiked ? unLikeMutate(feedId) : likeMutate(feedId);
    } else {
      navigateToLogin();
    }
  };

  return (
    <Wrapper>
      <InfoItem>
        <LikeIcon onClick={handleSubmitLike} />
        {likeCount}
      </InfoItem>

      <InfoItem onClick={onClickCommentIcon}>
        <ChatDotsIcon />
        {commentCount}
      </InfoItem>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  font: ${({ theme: { fonts } }) => fonts.displayB14};
  width: 100%;
  padding: 16px;
  text-align: center;
  display: flex;
  gap: 16px;
`;

const InfoItem = styled.div`
  display: flex;
  align-items: center;
  gap: 4px;

  svg {
    cursor: pointer;
  }
`;
