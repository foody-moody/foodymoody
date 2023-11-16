import { useMutation } from '@tanstack/react-query';
import { fetchLogin, fetchLogout, fetchRegister } from 'api/auth/login';
import { jwtDecode } from 'jwt-decode';
import { useLocation } from 'react-router-dom';
import { usePageNavigator } from 'hooks/usePageNavigator';
import {
  clearLoginInfo,
  setAccessToken,
  setRefreshToken,
  setUserInfo,
} from 'utils/localStorage';
import { PATH } from 'constants/path';

export const useLogin = () => {
  const { navigateToPath } = usePageNavigator();
  const location = useLocation();
  const from = location.state?.redirectedFrom?.pathname || PATH.HOME;
  //  사용자가 로그인 전에 접근하려고 했던 경로

  return useMutation({
    mutationFn: (body: LoginBody) => fetchLogin(body),
    onSuccess: (data) => {
      const { accessToken, refreshToken } = data;
      const payload = jwtDecode(accessToken);

      setAccessToken(accessToken);
      setRefreshToken(refreshToken);
      setUserInfo(JSON.stringify(payload));
      navigateToPath(from);
    },
    onError: () => {
      console.log('Login error:');
    },
  });
};

export const useLogout = () => {
  const { navigateToPath } = usePageNavigator();
  const location = useLocation();
  const from = location.state?.redirectedFrom?.pathname || PATH.HOME;

  return useMutation({
    mutationFn: () => fetchLogout(),
    onSuccess: () => {
      clearLoginInfo();
      navigateToPath(from);
    },
    onError: (error) => {
      console.log('Logout error:', error);
    },
  });
};

export const useRegister = () => {
  const { navigateToLogin } = usePageNavigator();

  return useMutation({
    mutationFn: (body: RegisterBody) => fetchRegister(body),
    onSuccess: () => {
      navigateToLogin();
    },
    onError: (error) => {
      console.error('Error during registration:', error);
    },
  });
};
