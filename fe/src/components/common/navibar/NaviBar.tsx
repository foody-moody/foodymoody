import { NavLink } from 'react-router-dom';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import {
  BellIcon,
  CollectableDefaultIcon,
  HomeIcon,
  SearchIcon,
  UserIcon,
  DotRoundIcon,
  PencilLineIcon,
} from 'components/common/icon/icons';
import { Logo } from 'components/common/logo/Logo';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { PATH } from 'constants/path';

export const NaviBar = () => {
  const { navigateToHome } = usePageNavigator();

  const NaviItems = [
    { label: '홈', icon: <HomeIcon />, path: PATH.HOME },
    { label: '탐색', icon: <SearchIcon />, path: PATH.SEARCH },
    {
      label: '컬렉션',
      icon: <CollectableDefaultIcon />,
      path: PATH.COLLECTION,
    },
    { label: '알림', icon: <BellIcon />, path: PATH.NOTI },
    { label: '프로필', icon: <UserIcon />, path: PATH.PROFILE },
    { label: '글쓰기', icon: <PencilLineIcon />, path: PATH.NEW_FEED },
  ];

  const NaviItemsM = [
    { label: '홈', icon: <HomeIcon />, path: PATH.HOME },
    { label: '탐색', icon: <SearchIcon />, path: PATH.SEARCH },
    { label: '글쓰기', icon: <PencilLineIcon />, path: PATH.NEW_FEED },
    {
      label: '컬렉션',
      icon: <CollectableDefaultIcon />,
      path: PATH.COLLECTION,
    },
    { label: '프로필', icon: <UserIcon />, path: PATH.PROFILE },
  ];

  return (
    <>
      <Wrapper>
        <Navi>
          <LogoBox>
            <Logo onClick={navigateToHome} />
          </LogoBox>
          {NaviItems.map((item) => (
            <NaviLink to={item.path}>
              {item.icon}
              <span>{item.label}</span>
            </NaviLink>
          ))}
        </Navi>

        <NaviM>
          {NaviItemsM.map((item) => (
            <NaviLink to={item.path}>{item.icon}</NaviLink>
          ))}
        </NaviM>

        <MoreBtn>
          <DotRoundIcon />
          <span>더보기</span>
        </MoreBtn>
      </Wrapper>
    </>
  );
};

const LogoBox = styled.div`
  margin-bottom: 16px;
`;

const Wrapper = styled.div`
  font: ${({ theme: { fonts } }) => fonts.displayM16};
  color: ${({ theme: { colors } }) => colors.black};
  border-right: 1px solid ${({ theme: { colors } }) => colors.black};
  background-color: ${({ theme: { colors } }) => colors.bgGray100};
  width: 272px;
  padding: 32px;
  transition: all 0.3s;
  height: 100vh;
  position: fixed;
  bottom: 0;
  left: 0;
  z-index: 1;

  ${media.lg} {
    width: 72px;
    padding: 32px 12px 12px 12px;

    svg {
      margin: 0 auto;
    }
  }

  ${media.xs} {
    background-color: ${({ theme: { colors } }) => colors.white};
    border-right: none;
    width: 100vw;
    height: 60px;
    position: fixed;
    padding: 0;
    bottom: 0;
  }
`;

const MoreBtn = styled.button`
  width: 100%;
  padding: 12px;
  display: flex;
  gap: 16px;
  align-items: center;
  margin-top: 16px;
  font: ${({ theme: { fonts } }) => fonts.displayM16};

  &:hover {
    background-color: ${({ theme: { colors } }) => colors.bgGray200};
    border-radius: ${({ theme: { radius } }) => radius.small};
    color: ${({ theme: { colors } }) => colors.black};
  }

  ${media.lg} {
    align-items: center;
    span {
      display: none;
    }
  }

  ${media.xs} {
    display: none;
  }
`;

const Navi = styled.nav`
  margin-top: 16px;
  display: flex;
  flex-direction: column;

  a {
    width: 100%;
    margin-top: 16px;
    display: flex;
    align-items: center;
    gap: 16px;
    padding: 12px;

    &:hover {
      background-color: ${({ theme: { colors } }) => colors.bgGray200};
      border-radius: ${({ theme: { radius } }) => radius.small};
      color: ${({ theme: { colors } }) => colors.black};
    }
  }

  ${media.lg} {
    align-items: center;
    span {
      display: none;
    }
  }
  ${media.xs} {
    display: none;
  }
`;

const NaviM = styled.nav`
  display: none;

  ${media.xs} {
    border-top: 1px solid ${({ theme: { colors } }) => colors.black};
    width: 100%;
    display: block;
    height: 60px;
    display: flex;
    justify-content: space-evenly;
    align-items: center;

    a {
      padding: 12px;
      display: flex;

      &:hover {
        background-color: ${({ theme: { colors } }) => colors.bgGray200};
        border-radius: ${({ theme: { radius } }) => radius.small};
        color: ${({ theme: { colors } }) => colors.black};
      }
    }
  }
`;

const NaviLink = styled(NavLink)`
  &.active {
    border: 0.8px solid ${({ theme: { colors } }) => colors.black};
    border-radius: ${({ theme: { radius } }) => radius.small};
    background-color: ${({ theme: { colors } }) => colors.bgGray200};
  }
`;
