import { useMutation, useQuery } from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { jwtDecode } from 'jwt-decode';
import { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useRecoilValue, useSetRecoilState } from 'recoil';
import {
  accessTokenState,
  refreshTokenState,
  userInfoState,
} from 'recoil/localStorage/atom';
import { useClearLoginInfo } from 'recoil/localStorage/useLoginInfo';
import { useToast } from 'recoil/toast/useToast';
import {
  fetchLogin,
  fetchLogout,
  fetchOAuthLogin,
  fetchRefresh,
  fetchRegister,
} from 'service/axios/auth/login';
import { QUERY_KEY } from 'service/constants/queryKey';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { PATH } from 'constants/path';

export const useLogin = () => {
  const toast = useToast();
  const navigate = useNavigate();
  const location = useLocation();
  const from = location.state?.redirectedFrom?.pathname || PATH.HOME;
  //  사용자가 로그인 전에 접근하려고 했던 경로

  const setAccessToken = useSetRecoilState(accessTokenState);
  const setRefreshToken = useSetRecoilState(refreshTokenState);
  const setUserInfo = useSetRecoilState(userInfoState);

  return useMutation({
    mutationFn: (body: LoginBody) => fetchLogin(body),
    onSuccess: (data) => {
      const { accessToken, refreshToken } = data;
      const payload = jwtDecode(accessToken);
      localStorage.setItem('accessToken', accessToken);
      localStorage.setItem('refreshToken', refreshToken);
      localStorage.setItem('userInfo', JSON.stringify(payload));
      setAccessToken(accessToken);
      setRefreshToken(refreshToken);
      setUserInfo(JSON.stringify(payload));
      navigate(from, { replace: true });
      toast.success('로그인 되었습니다.');
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useOAuthLogin = (code: string) => {
  const toast = useToast();
  const navigate = useNavigate();
  const location = useLocation();
  const from = location.state?.redirectedFrom?.pathname || PATH.HOME;
  //  사용자가 로그인 전에 접근하려고 했던 경로

  const setAccessToken = useSetRecoilState(accessTokenState);
  const setRefreshToken = useSetRecoilState(refreshTokenState);
  const setUserInfo = useSetRecoilState(userInfoState);

  const query = useQuery({
    queryKey: [QUERY_KEY.OAuth],
    queryFn: () => fetchOAuthLogin(code), // 이 코드로 백엔드 API 호출
    enabled: !!code,
  });

  useEffect(() => {
    console.log('code in query', code);

    if (query.isSuccess) {
      const { accessToken, refreshToken } = query.data;
      const payload = jwtDecode(accessToken);
      localStorage.setItem('accessToken', accessToken);
      localStorage.setItem('refreshToken', refreshToken);
      localStorage.setItem('userInfo', JSON.stringify(payload));
      setAccessToken(accessToken);
      setRefreshToken(refreshToken);
      setUserInfo(JSON.stringify(payload));
      navigate(from, { replace: true });
      toast.success('로그인 되었습니다.');
    }

    if (query.isError) {
      console.log('error', query.error);
    }
  }, [query.isSuccess, query.isError]);

  return query;
  // return useMutation({
  //   mutationFn: (code: string) => fetchOAuthLogin(code),
  //   onSuccess: (data) => {
  //     const { accessToken, refreshToken } = data;
  //     const payload = jwtDecode(accessToken);
  //     localStorage.setItem('accessToken', accessToken);
  //     localStorage.setItem('refreshToken', refreshToken);
  //     localStorage.setItem('userInfo', JSON.stringify(payload));
  //     setAccessToken(accessToken);
  //     setRefreshToken(refreshToken);
  //     setUserInfo(JSON.stringify(payload));
  //     navigate(from, { replace: true });
  //     toast.success('로그인 되었습니다.');
  //   },
  //   onError: (error: AxiosError<CustomErrorResponse>) => {
  //     const errorData = error?.response?.data;

  //     errorData && toast.error(errorData.message);
  //   },
  // });
};

export const useLogout = () => {
  const toast = useToast();
  const { navigateToPath } = usePageNavigator();
  const location = useLocation();
  const from = location.state?.redirectedFrom?.pathname || PATH.HOME;
  const clearLoginInfo = useClearLoginInfo();

  return useMutation({
    mutationFn: () => fetchLogout(),
    onSuccess: () => {
      clearLoginInfo();
      toast.success('로그아웃 되었습니다.');
      navigateToPath(from);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useRegister = () => {
  const toast = useToast();
  const navigate = useNavigate();

  return useMutation({
    mutationFn: (body: RegisterBody) => fetchRegister(body),
    onSuccess: () => {
      navigate(PATH.LOGIN, { replace: true });
      toast.success('회원가입 되었습니다.');
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useRefreshToken = () => {
  const setAccessToken = useSetRecoilState(accessTokenState);
  const setRefreshToken = useSetRecoilState(refreshTokenState);
  const setUserInfo = useSetRecoilState(userInfoState);

  const refreshToken = useRecoilValue(refreshTokenState);
  const userInfo = useRecoilValue(userInfoState);
  const clearLoginInfo = useClearLoginInfo();

  const refreshTokenMutation = useMutation(
    () => {
      // if (!refreshToken) throw new Error('No refresh token available');

      return fetchRefresh(refreshToken);
    },
    {
      onSuccess: (data) => {
        const { accessToken, refreshToken } = data;
        const payload = jwtDecode(accessToken);
        localStorage.setItem('accessToken', accessToken);
        localStorage.setItem('refreshToken', refreshToken);
        localStorage.setItem('userInfo', JSON.stringify(payload));
        setAccessToken(accessToken);
        setRefreshToken(refreshToken);
        setUserInfo(JSON.stringify(payload));
      },
      onError: (error: AxiosError<CustomErrorResponse>) => {
        const errorData = error?.response?.data;
        errorData && console.log(errorData.message);

        clearLoginInfo();
      },
    }
  );

  const INTERVAL_TIME = 10 * 1000;
  // TODO 30초마다 체크, 만료 시간 연장되면 더 늘리기
  useEffect(() => {
    if (!userInfo) {
      return;
    }

    const checkRefreshTokenExpiry = () => {
      try {
        const storedRefreshToken = localStorage.getItem('refreshToken');

        if (!storedRefreshToken) {
          return false;
        }

        const decoded = jwtDecode(storedRefreshToken);

        if (decoded && typeof decoded.exp === 'number') {
          const now = Date.now() / 1000;
          return decoded.exp < now;
        }
      } catch (error) {
        return true;
      }
    };

    const isRefreshTokenExpired = checkRefreshTokenExpiry();

    if (isRefreshTokenExpired) {
      console.log('refreshTokenExpired!!!!!!!!!!!!!1');
      clearLoginInfo();
      window.location.replace(PATH.HOME);
      return;
    }

    const checkInterval = setInterval(() => {
      const isExpiring = checkTokenExpiry(JSON.parse(userInfo));
      if (isExpiring) {
        refreshTokenMutation.mutate();
      }
    }, INTERVAL_TIME);

    return () => clearInterval(checkInterval);
  }, [userInfo, refreshTokenMutation]);
};

export const checkTokenExpiry = (userInfo: UserInfoType) => {
  const { exp } = userInfo;
  const now = Date.now() / 1000;
  const BEFORE_EXPIRY = 60;
  // exp: 만료 시간
  // 만료 1분 전인지 체크
  // TODO 만료 시간 연장되면 더 늘리기

  return exp - now < BEFORE_EXPIRY;
};
