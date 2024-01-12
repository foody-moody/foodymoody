import { Suspense } from 'react';
import { useLocation } from 'react-router-dom';
import { styled } from 'styled-components';
import { DeferredComponent } from 'components/common/skeleton/DeferredComponent';
import { FeedSkeleton } from 'components/common/skeleton/FeedSkeleton';
import { FeedMainList } from 'components/feedMain/FeedMainList';
import { DetailFeedModalPage } from './DetailFeedPage';
import { NewFeedModalPage } from './NewFeedPage';

export const HomePage = () => {
  const location = useLocation();
  const background = location.state && location.state.background;
  const isDetailFeedUrl = location.pathname.includes('/detail/feed');

  return (
    <Wrapper>
      <Suspense
        fallback={
          <DeferredComponent>
            <FeedSkeleton feedCount={10} />
          </DeferredComponent>
        }
      >
        <FeedMainList />
      </Suspense>
      {isDetailFeedUrl && <DetailFeedModalPage />}
      {background === 'newFeed' && <NewFeedModalPage />}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  width: 100%;
  position: relative;
  flex-direction: column;
  align-items: center;
`;
