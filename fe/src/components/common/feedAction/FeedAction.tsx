import { styled } from 'styled-components';
import { ChatDots, HeartBg, HeartFill } from '../icons';
import { useState } from 'react';

type Props = {
  likeCount: number;
  commentCount: number;
  onClick?: (id: number, name: string) => void;
};

// TODO 기본값 삭제
export const FeedAction: React.FC<Props> = ({
  likeCount = 12,
  commentCount = 11,
}) => {
  const [isLiked, setIsLiked] = useState(false);
  // TODO 로그인유무로 교체 const isLiked = isLogin ? feed.isLiked : false;
  // feed: query로 받아온 feed데이터

  const onToggleLike = () => {
    // TODO Mutation으로 교체
    // TODO 로그인false => mutate 불가

    setIsLiked(!isLiked);
  };

  const onNavigateToDetail = () => {
    // TODO navigate, url이 detail이면 return
  };

  const LikeIcon = isLiked ? HeartFill : HeartBg;

  return (
    <Wrapper>
      <InfoItem>
        <LikeIcon onClick={onToggleLike} />
        {likeCount}
      </InfoItem>
      <InfoItem>
        <ChatDots onClick={onNavigateToDetail} />
        {commentCount}
      </InfoItem>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  font: ${({ theme: { fonts } }) => fonts.displayB12};
  width: 100%;
  padding: 8px 16px;
  text-align: center;
  display: flex;
  gap: 16px;
  border: 1px solid red;
`;
// border삭제

const InfoItem = styled.div`
  display: flex;
  align-items: center;
  gap: 4px;

  svg {
    cursor: pointer;
  }
`;
