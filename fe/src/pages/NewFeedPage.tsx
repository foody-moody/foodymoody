import { Navigate, useLocation } from 'react-router-dom';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { Dim } from 'components/common/dim/Dim';
import { FeedEditor } from 'components/feedEditor/FeedEditor';
import { useAuthState } from 'hooks/auth/useAuth';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { PATH } from 'constants/path';

export const NewFeedModalPage = () => {
  const currentPath = useLocation();
  const { isLogin } = useAuthState();
  const { navigateToHome } = usePageNavigator();

  return isLogin ? (
    <>
      <Dim onClick={navigateToHome} />
      <Wrapper>
        <FeedEditor />
      </Wrapper>
    </>
  ) : (
    <Navigate to={PATH.LOGIN} replace state={{ redirectedFrom: currentPath }} />
  );
};

const Wrapper = styled.div`
  padding: 16px;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 100;
  max-width: 968px;
  min-width: 343px;
  width: 100%;
  height: 680px;

  ${media.md} {
    max-width: 580px;
    min-width: 379px;
    width: 100%;
    max-height: 88dvh;
    height: 100%;
    overflow: hidden;
  }
`;
