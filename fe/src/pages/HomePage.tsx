import loadable from '@loadable/component';
import { Suspense } from 'react';
import { useLocation } from 'react-router-dom';
import { styled } from 'styled-components';
import { CustomHelmet } from 'components/common/helmet/Helmet';
import { DeferredComponent } from 'components/common/skeleton/DeferredComponent';
import { FeedSkeleton } from 'components/common/skeleton/FeedSkeleton';
import { FeedMainList } from 'components/feedMain/FeedMainList';

// import { DetailFeedModalPage } from './DetailFeedPage';
// import { NewFeedModalPage } from './NewFeedPage';

const DetailFeedModalPage = loadable(() =>
  import('./DetailFeedPage').then((module) => module.DetailFeedModalPage)
);

const NewFeedModalPage = loadable(() =>
  import('./NewFeedPage').then((module) => module.NewFeedModalPage)
);

export const HomePage = () => {
  const location = useLocation();
  const background = location.state && location.state.background;
  const isDetailFeedUrl = location.pathname.includes('/detail/feed');

  return (
    <Wrapper>
      <CustomHelmet
        meta={{
          keywords:
            '푸디무디, 푸디무디 홈, 푸디무디 메인, 푸디무디 피드, 맛집, 리뷰, 후기, 푸디무디, 푸디모디, 포디모디, 포디무디, foodymoody, 음식, 식당, 맛집추천, 맛집리뷰, 맛집탐방, 맛집투어, 내돈내산, 진짜, 존맛,  위치, 주차, 예약',
          description: `'진짜'만 모은 맛잘알들의 맛집 컬렉션!
          맛집 컬렉터들의 '진짜' 맛집 후기를 모아보고, 나만의 맛집 컬렉션을 만들어보세요!`,
          ogTitle: '푸디무디 - 홈',
          ogDescription: `여기를 눌러 링크를 확인하세요`,
          ogImage:
            'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/images/fm-logo-with-text.png',
          ogUrl: 'https://foodymoody.site',
          ogType: 'website',
          ogSiteName: '푸디무디',
          ogLocale: 'ko_KR',
          twitterTitle: '푸디무디 - 홈',
          twitterDescription: `'진짜'만 모은 맛잘알들의 맛집 컬렉션!
          맛집 컬렉터들의 '진짜' 맛집 후기를 모아보고, 나만의 맛집 컬렉션을 만들어보세요!`,
          twitterImage:
            'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/images/fm-logo-with-text.png',
          twitterUrl: 'https://foodymoody.site',
        }}
      />
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
