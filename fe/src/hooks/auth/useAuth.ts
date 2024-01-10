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
