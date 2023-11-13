import { useMutation } from '@tanstack/react-query';
import { fetchLogin, fetchRegister } from 'api/auth/login';
import { jwtDecode } from 'jwt-decode';
import { useLocation } from 'react-router-dom';
import { usePageNavigator } from 'hooks/usePageNavigator';
import {
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

  const login = useMutation({
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

  return login;
};

export const useRegister = () => {
  const { navigateToLogin } = usePageNavigator();

  const register = useMutation({
    mutationFn: (body: RegisterBody) => fetchRegister(body),
    onSuccess: () => {
      navigateToLogin();
    },
    onError: (error) => {
      console.error('Error during registration:', error);
    },
  });

  return register;
};
