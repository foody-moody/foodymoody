import {
  // fetchLogin,
  fetchLogout,
  // fetchRegister,
  fetchUnRegister,
} from 'api/auth/login';
// import { jwtDecode } from 'jwt-decode';
import { useEffect } from 'react';
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

// export const useLogin = () => {
//   // const setIsLogin = useSetRecoilState(isLoginState);
//   const { navigateToPath } = usePageNavigator();
//   const location = useLocation();
//   const from = location.state?.redirectedFrom?.pathname || PATH.HOME;
//   //  사용자가 로그인 전에 접근하려고 했던 페이지의 정보

//   const handleLogin = async (body: LoginBody) => {
//     try {
//       const { accessToken, refreshToken } = await fetchLogin(body);

//       const payload = jwtDecode(accessToken);

//       setAccessToken(accessToken);
//       setRefreshToken(refreshToken);
//       setUserInfo(JSON.stringify(payload));
//       // setIsLogin(true); // 근데 이 상태도 새로고침하면 사라지지않나? => localstorage확인으로 변경
//       navigateToPath(from);
//     } catch (error) {
//       console.error('Login error:', error);
//     }
//   };

//   return { handleLogin };
// };

export const useLogout = () => {
  //훅일 필요가 있나
  // const setIsLogin = useSetRecoilState(isLoginState);
  const { navigateToHome } = usePageNavigator();

  const handleLogout = async () => {
    try {
      await fetchLogout();
      clearLoginInfo();
      // setIsLogin(false);
      navigateToHome();
    } catch (error) {
      console.error('Logout error:', error);
    }
  };

  return { handleLogout };
};

// export const useRegister = () => {
//   const { navigateToLogin } = usePageNavigator();

//   const handleRegister = async (body: RegisterBody) => {
//     try {
//       await fetchRegister(body);
//       // 성공시 로그인페이지로
//       navigateToLogin();
//     } catch (error) {
//       console.error('Error during registration:', error);
//     }
//   };
//   return { handleRegister };
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
