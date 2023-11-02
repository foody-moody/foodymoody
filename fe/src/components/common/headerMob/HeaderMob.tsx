import React from 'react';
import { useLocation } from 'react-router-dom';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { GearIcon, LogoLarge } from '../icon/icons';
import { NotiIcon } from '../notiIcon/NotiIcon';
import { PATH } from 'constants/path';

export const HeaderMob: React.FC = () => {
  const { navigateToHome } = usePageNavigator();
  const currentLocation = useLocation();
  const isProfilePage = currentLocation.pathname === PATH.PROFILE;

  return (
    <Wrapper>
      <FlexRowBox>
        <LogoLarge onClick={navigateToHome} />
        <ContentRight>
          <NotiIcon />
          {isProfilePage && <GearIcon />}
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
