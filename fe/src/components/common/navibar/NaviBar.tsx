import { NavLink } from 'react-router-dom';
import { useLogout } from 'service/queries/auth';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import {
  CollectableDefaultIcon,
  HomeIcon,
  SearchIcon,
  UserIcon,
  DotRoundIcon,
  PencilLineIcon,
} from 'components/common/icon/icons';
import { Logo } from 'components/common/logo/Logo';
import { useAuthState } from 'hooks/auth/useAuth';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { Dropdown } from '../dropdown/Dropdown';
import { DropdownRow } from '../dropdown/DropdownRow';
import { NotiIcon } from '../icon/NotiIcon';
import { PATH } from 'constants/path';

export const NaviBar = () => {
  const { navigateToHome, navigateToLogin, navigateToProfileSetting } =
    usePageNavigator();
  const { mutate: logoutMutate } = useLogout();
  const { isLogin } = useAuthState();
  const NaviItems = [
    { label: '홈', icon: <HomeIcon />, path: PATH.HOME },
    { label: '탐색', icon: <SearchIcon />, path: PATH.SEARCH },
    {
      label: '컬렉션',
      icon: <CollectableDefaultIcon />,
      path: PATH.COLLECTION,
    },
    { label: '알림', icon: <NotiIcon />, path: PATH.NOTI },
    { label: '프로필', icon: <UserIcon />, path: PATH.PROFILE },
    {
      label: '글쓰기',
      icon: <PencilLineIcon />,
      path: PATH.NEW_FEED,
      state: { background: 'newFeed' },
    },
  ];

  const NaviItemsM = [
    { label: '홈', icon: <HomeIcon />, path: PATH.HOME },
    { label: '탐색', icon: <SearchIcon />, path: PATH.SEARCH },
    {
      label: '글쓰기',
      icon: <PencilLineIcon />,
      path: PATH.NEW_FEED,
      state: { background: 'newFeed' },
    },
    {
      label: '컬렉션',
      icon: <CollectableDefaultIcon />,
      path: PATH.COLLECTION,
    },
    { label: '프로필', icon: <UserIcon />, path: PATH.PROFILE },
  ];

  const publicMenu = [
    {
      id: 1,
      content: '로그인',
      onClick: () => {
        navigateToLogin();
      },
    },
  ];

  const privateMenu = [
    {
      id: 1,
      content: '설정',
      onClick: () => {
        navigateToProfileSetting();
      },
    },
    {
      id: 2,
      content: '문제 신고',
      onClick: () => {},
    },
    {
      id: 3,
      content: '로그아웃',
      onClick: () => {
        logoutMutate();
      },
    },
  ];

  const dropdownRows = isLogin ? privateMenu : publicMenu;

  return (
    <>
      <Wrapper>
        <Navi>
          <LogoBox>
            <Logo onClick={navigateToHome} />
          </LogoBox>
          {NaviItems.map((item) => (
            <NaviLink to={item.path} state={item.state} key={item.label}>
              {item.icon}
              <span>{item.label}</span>
            </NaviLink>
          ))}
        </Navi>

        <NaviM>
          {NaviItemsM.map((item) => (
            <NaviLink to={item.path} state={item?.state} key={item.label}>
              {item.icon}
            </NaviLink>
          ))}
        </NaviM>

        <Dropdown
          opener={
            <MoreBtn>
              <DotRoundIcon />
              <span>더보기</span>
            </MoreBtn>
          }
        >
          {dropdownRows.map((item) => (
            <DropdownRow key={item.id} onClick={item.onClick}>
              {item.content}
            </DropdownRow>
          ))}
        </Dropdown>
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
  z-index: 100;

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
  width: 208px;
  padding: 12px;
  display: flex;
  gap: 16px;
  align-items: center;
  margin-top: 16px;
  font: ${({ theme: { fonts } }) => fonts.displayM16};
  transition: all 0.3s;
  &:hover {
    background-color: ${({ theme: { colors } }) => colors.bgGray200};
    border-radius: ${({ theme: { radius } }) => radius.small};
    color: ${({ theme: { colors } }) => colors.black};
  }

  ${media.lg} {
    align-items: center;
    width: 47px;
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
  align-items: center;

  a {
    width: 100%;
    margin-top: 16px;
    display: flex;
    align-items: center;
    gap: 16px;
    padding: 12px;
    height: 56px;

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
