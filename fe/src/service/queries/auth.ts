import { useMutation, useQuery } from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { jwtDecode } from 'jwt-decode';
import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useRecoilValue, useSetRecoilState } from 'recoil';
import {
  accessTokenState,
  refreshTokenState,
  userInfoState,
} from 'recoil/localStorage/atom';
import { useToast } from 'recoil/toast/useToast';
import {
  fetchLogin,
  fetchLogout,
  fetchRefresh,
  fetchRegister,
} from 'service/axios/auth/login';
import { QUERY_KEY } from 'service/constants/queryKey';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { clearLoginInfo } from 'utils/localStorage';
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

export const useLogout = () => {
  const toast = useToast();
  const { navigateToPath } = usePageNavigator();
  const location = useLocation();
  const from = location.state?.redirectedFrom?.pathname || PATH.HOME;

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
  const refreshToken = useRecoilValue(refreshTokenState);
  const userInfo = useRecoilValue(userInfoState);

  const setAccessToken = useSetRecoilState(accessTokenState);
  const setRefreshToken = useSetRecoilState(refreshTokenState);
  const setUserInfo = useSetRecoilState(userInfoState);
  console.log('userInfo', userInfo);

  const refreshTokenMutation = useMutation(
    () => {
      if (!refreshToken) throw new Error('No refresh token available');
      return fetchRefresh(refreshToken);
    },
    {
      onSuccess: (data) => {
        const { accessToken, refreshToken } = data;
        const payload = jwtDecode(accessToken);

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

  const INTERVAL_TIME = 5 * 1000;
  useEffect(() => {
    const checkInterval = setInterval(() => {
      if (!userInfo) return;
      const isExpiring = checkTokenExpiry(JSON.parse(userInfo));
      if (isExpiring) {
        refreshTokenMutation.mutate();
      }
    }, INTERVAL_TIME);

    return () => clearInterval(checkInterval);
  }, [userInfo, refreshTokenMutation]);

  // 다른 필요한 로직 추가
};

// export const useRefreshToken = () => {
//   const [isTokenExpiring, setIsTokenExpiring] = useState(false);
//   const refreshToken = useRecoilValue(refreshTokenState);
//   const userInfo = useRecoilValue(userInfoState);
//   console.log('userInfo', userInfo);
//   // console.log('userInfo', JSON.parse(userInfo));

//   const setAccessToken = useSetRecoilState(accessTokenState);
//   const setRefreshToken = useSetRecoilState(refreshTokenState);
//   const setUserInfo = useSetRecoilState(userInfoState);

//   // const INTERVAL_TIME = 30 * 1000;
//   const INTERVAL_TIME = 5 * 1000;
//   // 30초마다 토큰 만료 여부 체크
//   // TODO 만료 시간 연장되면 더 늘리기
//   useEffect(() => {
//     const checkInterval = setInterval(() => {
//       if (!userInfo) return;
//       const isExpiring = checkTokenExpiry(userInfo);
//       // const isExpiring = checkTokenExpiry(JSON.parse(userInfo));
//       setIsTokenExpiring(isExpiring);
//     }, INTERVAL_TIME);

//     return () => clearInterval(checkInterval);
//   }, [userInfo]);

//   const tokenQuery = useQuery({
//     queryKey: [QUERY_KEY.refresh],
//     queryFn: () => {
//       if (!refreshToken) return Promise.reject();
//       return fetchRefresh(refreshToken);
//     },
//     enabled: isTokenExpiring, // 토큰 만료 여부에 따라 쿼리 활성화
//     refetchOnWindowFocus: true,
//     refetchOnMount: true,
//   });

//   useEffect(() => {
//     if (tokenQuery.data) {
//       const { accessToken, refreshToken } = tokenQuery.data;
//       const payload = jwtDecode(accessToken);
//       const ref = jwtDecode(refreshToken);
//       console.log('payload ref', ref);

//       setAccessToken(accessToken);
//       setRefreshToken(refreshToken);
//       setUserInfo(JSON.stringify(payload));
//     }
//   }, [tokenQuery.data]);
// };

export const checkTokenExpiry = (userInfo: any) => {
  const { exp } = userInfo;
  console.log('exp', exp);

  const now = Date.now() / 1000;
  const BEFORE_EXPIRY = 60;
  // exp: 만료 시간
  // 만료 1분 전인지 체크
  // TODO 만료 시간 연장되면 더 늘리기
  console.log('expire ??', exp - now < BEFORE_EXPIRY);
  console.log('valid ??', exp - now > 0);

  // return exp - now < BEFORE_EXPIRY && exp - now > 0;
  return exp - now < BEFORE_EXPIRY;
};
