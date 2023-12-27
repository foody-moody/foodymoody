import { Suspense, useRef } from 'react';
import { useLocation } from 'react-router-dom';
import { styled } from 'styled-components';
import { Dim } from 'components/common/dim/Dim';
import { FlexRowBox } from 'components/common/feedUserInfo/FeedUserInfo';
import { CloseSmallIcon } from 'components/common/icon/icons';
import { CommentSkeleton } from 'components/common/skeleton/CommentSkeleton';
import { DeferredComponent } from 'components/common/skeleton/DeferredComponent';
import { FollowList } from 'components/follow/followList/FollowList';
import { usePageNavigator } from 'hooks/usePageNavigator';

export const FollowModalPage = () => {
  const { navigateToBack } = usePageNavigator();
  const { pathname } = useLocation();
  const followType = pathname.includes('/followers')
    ? 'followers'
    : 'followings';
  const wrapperRef = useRef<HTMLDivElement>(null);

  return (
    <>
      <Dim
        onClick={() => {
          navigateToBack();
        }}
      />
      <Wrapper ref={wrapperRef}>
        <Header>
          <Title>{followType === 'followers' ? '팔로워' : '팔로잉'}</Title>
          <CloseSmallIcon
            onClick={() => {
              navigateToBack();
            }}
          />
        </Header>
        <Suspense
          fallback={
            <DeferredComponent>
              <CommentSkeleton itemCount={4} />
            </DeferredComponent>
          }
        >
          <FollowList rootRef={wrapperRef} followType={followType} />
        </Suspense>
      </Wrapper>
    </>
  );
};

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  background: ${({ theme: { colors } }) => colors.white};
  padding: 16px 12px 16px 16px;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 100;
  max-width: 450px;
  min-width: 320px;
  max-height: 450px;
  min-height: 320px;
  width: 100%;

  overscroll-behavior: contain;
`;

const Header = styled(FlexRowBox)`
  padding: 0 0 12px 0;
  width: 100%;
  justify-content: flex-end;
  align-items: center;
`;

const Title = styled.p`
  flex: 1;
  text-align: center;
  margin-left: 30px;
  font: ${({ theme: { fonts } }) => fonts.displayB16};
`;
