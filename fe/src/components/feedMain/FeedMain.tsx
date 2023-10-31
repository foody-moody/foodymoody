import { styled } from 'styled-components';
import { Badge } from 'components/common/badge/Badge';
import { Carousel } from 'components/common/carousel/Carousel';
import { FeedAction } from 'components/common/feedAction/FeedAction';
import { FeedUserInfo } from 'components/common/feedUserInfo/FeedUserInfo';
import { useReadMore } from 'hooks/useReadMore';

type Props = {
  feed: MainFeed;
};

export const MainFeedItem: React.FC<Props> = ({ feed }) => {
  const { displayText, toggleReadMore, readMore } = useReadMore(feed.review);

  return (
    <Wrapper>
      <Info>
        <FeedUserInfo
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

      <FeedAction likeCount={feed.likeCount} commentCount={feed.commentCount} />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  max-width: 566px;
  min-width: 340px;
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
