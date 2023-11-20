// import { jwtDecode } from 'jwt-decode';
import { useEffect } from 'react';
import {
  // fetchLogin,
  // fetchLogout,
  // fetchRegister,
  fetchUnRegister,
} from 'service/axios/auth/login';
// import { useLocation } from 'react-router-dom';
import { usePageNavigator } from 'hooks/usePageNavigator';
import {
  clearLoginInfo,
  getAccessToken,
  getRefreshToken,
  getUserInfo,
  // setAccessToken,
  // setRefreshToken,
  // setUserInfo,
} from 'utils/localStorage';

// import { PATH } from 'constants/path';

export const useAuthState = () => {
  const isLogin = !!getAccessToken() && !!getRefreshToken() && !!getUserInfo();
  const userInfo = getUserInfo();

  return { isLogin, userInfo };
};

export const useRefreshToken = () => {
  useEffect(() => {
    const refreshToken = getRefreshToken();

    if (!refreshToken) return;

    const getAccessTokenByRefreshToken = async () => {
      try {
        // const { accessToken, refreshToken } = await //Token 요청;
        // setAccessToken(accessToken);
        // setRefreshToken(refreshToken);
      } catch (error) {
        console.error('Error during fetching access token:', error);
        // 로그아웃
        clearLoginInfo();
      }
    };

    getAccessTokenByRefreshToken();
  }, []);
};

// export const useLogout = () => {
//   // const setIsLogin = useSetRecoilState(isLoginState);
//   const { navigateToHome } = usePageNavigator();

//   const handleLogout = async () => {
//     try {
//       await fetchLogout();
//       clearLoginInfo();
//       // setIsLogin(false);
//       navigateToHome();
//     } catch (error) {
//       console.error('Logout error:', error);
//     }
//   };

//   return { handleLogout };
// };

export const useUnRegister = () => {
  const { navigateToHome } = usePageNavigator();

  const handleUnRegister = async (id: string) => {
    try {
      await fetchUnRegister(id);

      navigateToHome();
    } catch (error) {
      console.error('Error during unregistration:', error);
    }
  };

  return { handleUnRegister };
};
