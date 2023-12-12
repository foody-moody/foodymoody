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
  isLiked = false,
  likeCount = 0,
  commentCount = 0,
  onClickCommentIcon,
}) => {
  const { id: param } = useParams() as { id: string };
  const { navigateToLogin } = usePageNavigator();
  const { isLogin } = useAuthState();
  // const [isLiked, setIsLiked] = useState(false);
  const { mutate: likeMutate } = usePostFeedLike(param);
  const { mutate: unLikeMutate } = useDeleteFeedLike(param);
  // TODO 로그인유무로 교체 const isLiked = isLogin ? feed.isLiked : false;
  // feed: query로 받아온 feed데이터
  const LikeIcon = isLiked ? HeartFillIcon : HeartBgIcon;
  const likeFn = isLiked ? unLikeMutate : likeMutate;

  const handleSubmitLike = () => {
    // setIsLiked(!isLiked); // TODO 좋아요 연결시 삭제

    if (isLogin) {
      // likeMutate(feedId);
      likeFn(feedId);
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
