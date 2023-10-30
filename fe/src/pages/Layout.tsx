import { Outlet } from 'react-router-dom';
import { styled } from 'styled-components';
import { NaviBar } from 'components/common/navibar/NaviBar';

export const Layout = () => {
  return (
    <>
      <Body>
        <NaviBar></NaviBar>
        <Outlet></Outlet>
      </Body>
    </>
  );
};

const Body = styled.div``;
