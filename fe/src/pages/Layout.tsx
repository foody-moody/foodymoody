import { Outlet } from 'react-router-dom';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { NaviBar } from 'components/common/navibar/NaviBar';

export const Layout = () => {
  return (
    <>
      <NaviBar />
      <Body>
        <Outlet />
      </Body>
    </>
  );
};

const Body = styled.div`
  width: 100%;
  height: 100%;
  padding: 0 0 0 272px;

  ${media.lg} {
    padding: 0 0 0 72px;
  }

  ${media.xs} {
    padding: 0;
  }
`;