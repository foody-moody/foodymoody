import loadable from '@loadable/component';
import { Suspense, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { Collection } from 'components/collection/profile/Collection';
import { DeferredComponent } from 'components/common/skeleton/DeferredComponent';
import { ProfileUserInfoSkeleton } from 'components/common/skeleton/ProfileUserInfoSkeleton';
import { UserFeedTabs } from 'components/common/userFeedTabs/UserFeedTabs';
import { FeedLikeList } from 'components/feedLike/FeedLikeList';
import { FeedProfileList } from 'components/feedProfile/FeedPofileList';
import { ProfileUserInfo } from 'components/profileUserInfo/ProfileUserInfo';
// import { DetailFeedModalPage } from './DetailFeedPage';
import { FollowModalPage } from './FollowPage';

const DetailFeedModalPage = loadable(() =>
  import('./DetailFeedPage').then((module) => module.DetailFeedModalPage)
);

export const ProfilePage = () => {
  /* TODO. data.myFeed 데이터 생기면 추가하기 */
  const location = useLocation();
  const background = location.state && location.state.background;

  const [index, setIndex] = useState(0);

  const handleFeedTab = (index: number) => {
    setIndex(index);
  };

  return (
    <Wrapper>
      <ContentWrapper>
        <ProfileWrapper>
          <Suspense
            fallback={
              <DeferredComponent>
                <ProfileUserInfoSkeleton />
              </DeferredComponent>
            }
          >
            <ProfileUserInfo />
          </Suspense>
        </ProfileWrapper>
        <UserFeedTabs index={index} onClick={handleFeedTab} />
        {index === 0 ? (
          <FeedProfileList />
        ) : index === 1 ? (
          <Collection />
        ) : (
          <FeedLikeList />
        )}
      </ContentWrapper>

      {background === 'profileDetailFeed' && <DetailFeedModalPage />}
      {background === 'followings' && <FollowModalPage />}
      {background === 'followers' && <FollowModalPage />}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  transition: all 0.2s ease-in-out;
`;

const ContentWrapper = styled.div`
  width: 566px;
  /* height: 100%; */
  height: 100dvh;
  border-left: 1px solid ${({ theme: { colors } }) => colors.black};
  border-right: 1px solid ${({ theme: { colors } }) => colors.black};

  ${media.md} {
    max-width: 568px;
    width: 100%;
  }

  ${media.xs} {
    border-left: none;
    border-right: none;
  }
`;

const ProfileWrapper = styled.div`
  padding: 24px 16px 16px 16px;
  border-bottom: 1px solid ${({ theme: { colors } }) => colors.black};

  ${media.xs} {
    padding: 16px;
  }
`;
