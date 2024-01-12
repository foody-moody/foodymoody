import { Suspense, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { CollectionContainer } from 'components/collection/CollectionContainer';
import { DeferredComponent } from 'components/common/skeleton/DeferredComponent';
import { ProfileUserInfoSkeleton } from 'components/common/skeleton/ProfileUserInfoSkeleton';
import { UserFeedTabs } from 'components/common/userFeedTabs/UserFeedTabs';
import { LayoutButton } from 'components/layoutButton/LayoutButton';
import { ProfileUserInfo } from 'components/profileUserInfo/ProfileUserInfo';
import { FollowModalPage } from './FollowPage';

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
        {/* defaultFeed, collection, likedFeed 컴포넌트로 분리 */}
        {index === 0 ? (
          <FeedsWrapper>
            {MOCK_FEEDS.map((feed) => (
              <img
                key={feed.id}
                src={feed.imageUrl}
                alt={feed.imageUrl}
                onClick={() => {}}
              />
            ))}
          </FeedsWrapper>
        ) : (
          <Collection>
            <Header>
              <HeaderLeft>
                <Title>콜렉션</Title>
                <CollectionCounter>{12}</CollectionCounter>
              </HeaderLeft>
              <LayoutButton />
            </Header>
            <Contents>
              <CollectionContainer />
            </Contents>
          </Collection>
        )}
      </ContentWrapper>

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
  height: 100%;
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

const FeedsWrapper = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2px;

  img {
    cursor: pointer;
  }
`;

const generateDefaultImage = (imageUrl: string) =>
  `https://source.boringavatars.com/beam/${imageUrl}?colors=FF4E50,FC913A,F9D423,EDE574,E1F5C4&square`;

const MOCK_FEEDS = Array.from({ length: 20 }, (_, index) => ({
  id: index + 1,
  imageUrl: generateDefaultImage(`githubrandomProfileimageurl${index + 1}`),
}));

/* 컬렉션 */
const Collection = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;

const Header = styled.div`
  border-top: 1px solid ${({ theme: { colors } }) => colors.black};
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;
`;

const HeaderLeft = styled.div`
  display: flex;
  align-items: center;
  gap: 4px;
`;

const Title = styled.h2`
  font: ${({ theme: { fonts } }) => fonts.displayB14};
  color: ${({ theme: { colors } }) => colors.black};
`;

const CollectionCounter = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayB14};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;
const Contents = styled.div`
  width: 100%;
  height: 100%;
`;
