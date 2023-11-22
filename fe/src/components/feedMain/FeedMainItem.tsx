import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { Badge } from 'components/common/badge/Badge';
import { Carousel } from 'components/common/carousel/Carousel';
import { FeedAction } from 'components/common/feedAction/FeedAction';
import { FeedUserInfo } from 'components/common/feedUserInfo/FeedUserInfo';
import { useReadMore } from 'hooks/useReadMore';
import { PATH } from 'constants/path';

type Props = {
  feed: MainFeed;
};

export const MainFeedItem: React.FC<Props> = ({ feed }) => {
  const { displayText, toggleReadMore, readMore } = useReadMore(feed.review);
  const navigate = useNavigate();

  const handleOpenDetailFeed = () => {
    navigate(PATH.DETAIL_FEED + '/' + feed.id, {
      state: { background: 'detailFeed' },
    });
  };

  return (
    <Wrapper>
      <Info>
        <FeedUserInfo
          feedId={feed.id}
          member={feed.member}
          createdAt={feed.createdAt}
          location={feed.location}
        />
      </Info>

      <Content>
        <Review>
          {displayText}
          <ReadMoreBtn onClick={toggleReadMore}>
            {readMore ? '접기' : '더보기'}
          </ReadMoreBtn>
        </Review>
        <StoreMoodList>
          {feed.storeMood.map((storeMood) => (
            <Badge variant="store" badge={storeMood} key={storeMood.id} />
          ))}
        </StoreMoodList>
      </Content>

      <Carousel images={feed.images}></Carousel>

      <FeedAction
        likeCount={feed.likeCount}
        commentCount={feed.commentCount}
        onClickCommentIcon={handleOpenDetailFeed}
      />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  max-width: 566px;
  min-width: 340px;
  background-color: ${({ theme: { colors } }) => colors.white};
  width: 100%;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
`;

const Info = styled.div`
  padding: 16px 16px 0 16px;
`;

const Content = styled.div`
  padding: 16px;
`;

const Review = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  color: ${({ theme: { colors } }) => colors.black};
`;

const ReadMoreBtn = styled.button`
  margin-left: 8px;
  font: ${({ theme: { fonts } }) => fonts.displayB14};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;

const StoreMoodList = styled.div`
  display: flex;
  gap: 8px;
  margin-top: 16px;
`;
