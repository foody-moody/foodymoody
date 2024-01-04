import { forwardRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { StoreMoodBadge } from 'components/common/badge/StoreMoodBadge';
import { Carousel } from 'components/common/carousel/Carousel';
import { FeedAction } from 'components/common/feedAction/FeedAction';
import { FeedUserInfo } from 'components/common/feedUserInfo/FeedUserInfo';
import { useReadMore } from 'hooks/useReadMore';
import { PATH } from 'constants/path';

type Props = {
  feed: MainFeed;
};

export const MainFeedItem = forwardRef<HTMLLIElement, Props>(
  ({ feed }, ref) => {
    const { displayText, toggleReadMore, readMore, isLongText } = useReadMore(
      feed.review
    );
    const navigate = useNavigate();

    const handleOpenDetailFeed = () => {
      navigate(PATH.DETAIL_FEED + '/' + feed.id, {
        state: { background: 'detailFeed' },
      });
    };

    const isUpdated = feed.createdAt !== feed.updatedAt;

    return (
      <Wrapper ref={ref}>
        <Info>
          <FeedUserInfo
            feedId={feed.id}
            member={feed.member}
            createdAt={isUpdated ? feed.updatedAt : feed.createdAt}
            isUpdated={isUpdated}
            location={feed.location}
            thumbnail={feed.images[0]?.image.url}
          />
        </Info>

        <Content>
          <Review>
            {displayText}
            {isLongText && (
              <ReadMoreBtn onClick={toggleReadMore}>
                ...{readMore ? '접기' : '더보기'}
              </ReadMoreBtn>
            )}
          </Review>
          <StoreMoodList>
            {feed.storeMood.map((storeMood) => (
              <StoreMoodBadge name={storeMood.name} key={storeMood.id} />
            ))}
          </StoreMoodList>
        </Content>

        <Carousel images={feed.images}></Carousel>

        <FeedAction
          feedId={feed.id}
          likeCount={feed.likeCount}
          isLiked={feed.isLiked}
          commentCount={feed.commentCount}
          onClickCommentIcon={handleOpenDetailFeed}
        />
      </Wrapper>
    );
  }
);

const Wrapper = styled.li`
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
  /* margin-left: 8px; */
  font: ${({ theme: { fonts } }) => fonts.displayB14};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;

const StoreMoodList = styled.div`
  display: flex;
  gap: 8px;
  margin-top: 16px;
`;
