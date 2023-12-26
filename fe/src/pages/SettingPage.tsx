import { Outlet, NavLink } from 'react-router-dom';
import { styled } from 'styled-components';
import { PATH } from 'constants/path';

export const SettingPage = () => {
  return (
    <Wrapper>
      <Title>설정</Title>
      <Tap>
        <TapItem to={PATH.SETTING_PROFILE}>프로필 수정</TapItem>
        <TapItem to={PATH.SETTING_NOTI}>알림 설정</TapItem>
        <TapItem to={PATH.SETTING_ACCOUNT}>계정 설정</TapItem>
      </Tap>
      <Outlet />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  max-width: 564px;
  width: 100%;
  display: flex;
  flex-direction: column;
  margin: 40px auto 60px auto;
  gap: 20px;
`;

const Tap = styled.div`
  display: flex;
  width: 100%;
  border-bottom: 1px solid black;
  align-items: center;
`;
const TapItem = styled(NavLink)`
  padding: 8px 16px;
  flex-shrink: 0;
  font: ${({ theme: { fonts } }) => fonts.displayM16};

  &.active {
    margin: -1px;
    color: ${({ theme: { colors } }) => colors.orange};
    border-bottom: 2px solid ${({ theme: { colors } }) => colors.orange};
  }
`;

const Title = styled.h1`
  font: ${({ theme: { fonts } }) => fonts.displayB24};
  padding: 0 16px;
`;
