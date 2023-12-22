import React from 'react';
import { useLocation } from 'react-router-dom';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { NotiIcon } from '../icon/NotiIcon';
import { GearIcon, LogoLarge, SantaHat } from '../icon/icons';
import { PATH } from 'constants/path';

export const HeaderMob: React.FC = () => {
  const { navigateToHome, navigateToSetting } = usePageNavigator();
  const currentLocation = useLocation();
  const allowedPaths = [
    PATH.PROFILE,
    PATH.PASSWORD,
    PATH.PROFILE_EDIT,
    PATH.ACCOUNT,
  ];
  const accessToSetting = allowedPaths.includes(currentLocation.pathname);

  return (
    <Wrapper>
      <FlexRowBox>
        <StyledHat />
        <LogoLarge onClick={navigateToHome} />
        <ContentRight>
          <NotiIcon />
          {accessToSetting && <GearIcon onClick={navigateToSetting} />}
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

const StyledHat = styled(SantaHat)`
  display: block;
  position: absolute;
  top: 0px;
  left: 100px;
`;
