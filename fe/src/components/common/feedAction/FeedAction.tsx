import { useState } from 'react';
import { styled } from 'styled-components';
import { ChatDotsIcon, HeartBgIcon, HeartFillIcon } from '../icon/icons';

type Props = {
  likeCount: number;
  commentCount: number;
  onClickCommentIcon?: () => void;
};

export const FeedAction: React.FC<Props> = ({
  likeCount,
  commentCount,
  onClickCommentIcon,
}) => {
  const [isLiked, setIsLiked] = useState(false);
  // TODO 로그인유무로 교체 const isLiked = isLogin ? feed.isLiked : false;
  // feed: query로 받아온 feed데이터
  const LikeIcon = isLiked ? HeartFillIcon : HeartBgIcon;

  const handleToggleLike = () => {
    // TODO Mutation으로 교체
    // TODO 로그인false => mutate 불가

    setIsLiked(!isLiked);
  };

  return (
    <Wrapper>
      <InfoItem>
        <LikeIcon onClick={handleToggleLike} />
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
