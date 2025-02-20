import React from 'react';
import { useLocation } from 'react-router-dom';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { NotiIcon } from '../icon/NotiIcon';
import { GearIcon, LogoLarge } from '../icon/icons';
import { PATH } from 'constants/path';

export const HeaderMob: React.FC = () => {
  const { navigateToHome, navigateToProfileSetting, navigateToNoti } =
    usePageNavigator();
  const currentLocation = useLocation();
  const allowedPaths = [PATH.PROFILE, PATH.PASSWORD, PATH.HOME, PATH.NOTI];
  const accessToSetting = allowedPaths.includes(currentLocation.pathname);

  return (
    <Wrapper>
      <FlexRowBox>
        <LogoLarge onClick={navigateToHome} />
        <ContentRight>
          <NotiIcon onClick={navigateToNoti} />
          {accessToSetting && <GearIcon onClick={navigateToProfileSetting} />}
        </ContentRight>
      </FlexRowBox>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: none;
  width: 100%;
  padding: 16px;
  border-bottom: 1px solid ${({ theme: { colors } }) => colors.black};
  background-color: ${({ theme: { colors } }) => colors.bgGray50};

  ${media.xs} {
    position: fixed;
    display: block;
    z-index: 100;
    background-color: ${({ theme: { colors } }) => colors.bgGray100};
  }

  svg {
    cursor: pointer;
  }
`;

const FlexRowBox = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const ContentRight = styled(FlexRowBox)`
  gap: 12px;
`;
