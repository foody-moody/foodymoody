import loadable from '@loadable/component';
import { Suspense } from 'react';
import { useLocation } from 'react-router-dom';
import { styled } from 'styled-components';
import { Spinner } from 'components/common/loading/spinner';
import { DeferredComponent } from 'components/common/skeleton/DeferredComponent';
import { FeedSkeleton } from 'components/common/skeleton/FeedSkeleton';
import { FeedMainList } from 'components/feedMain/FeedMainList';
// import { DetailFeedModalPage } from './DetailFeedPage';
import { NewFeedModalPage } from './NewFeedPage';

const SpinnerPage = () => {
  return (
    <div style={{ width: '100%', height: '100%', background: 'red' }}>
      <Spinner isLoading />
    </div>
  );
};

const DetailFeedModalPage = loadable(
  () => import('./DetailFeedPage').then((module) => module.DetailFeedModalPage),
  {
    fallback: <SpinnerPage />,
  }
);

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
