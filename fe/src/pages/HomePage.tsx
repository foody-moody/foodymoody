import { useLocation } from 'react-router-dom';
import { useAllFeeds } from 'service/queries/feed';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { MainFeedItem } from 'components/feedMain/FeedMainItem';
import { useIntersectionObserver } from 'hooks/useObserver';
import { DetailFeedModalPage } from './DetailFeedPage';
import { NewFeedModalPage } from './NewFeedPage';

export const HomePage = () => {
  const location = useLocation();
  const background = location.state && location.state.background;

  /* TODO. 에러, 로딩, 데이터 아예 없을 경우, 끝까지 봤을 경우 처리 */
  const {
    feeds,
    hasNextPage,
    fetchNextPage,
    // isLoading,
    // error,
  } = useAllFeeds();

  const { observeTarget } = useIntersectionObserver({
    callbackFn: () => {
      hasNextPage && fetchNextPage();
    },
  });

  return (
    <Wrapper>
      {feeds?.map((feed: MainFeed, index: number) => {
        const isLastItem = index === feeds.length - 1;

        return (
          <MainFeedItem
            feed={feed}
            key={feed.id}
            ref={isLastItem ? observeTarget : null}
          />
        );
      })}

      {background === 'detailFeed' && <DetailFeedModalPage />}
      {background === 'newFeed' && <NewFeedModalPage />}
    </Wrapper>
  );
};

const Wrapper = styled.ul`
  display: flex;
  width: 100%;
  position: relative;
  flex-direction: column;
  gap: 16px;
  padding: 32px 0;
  align-items: center;

  ${media.xs} {
    padding-bottom: 59px;
  }
`;
