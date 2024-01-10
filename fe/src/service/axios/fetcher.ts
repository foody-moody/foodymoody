import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
import { useRecoilValue } from 'recoil';
import { accessTokenState } from 'recoil/localStorage/atom';
import {
  clearLoginInfo,
  getAccessToken,
  getRefreshToken,
  setAccessToken,
  setRefreshToken,
  setUserInfo,
} from 'utils/localStorage';
import { fetchRefresh } from './auth/login';

const { MODE, VITE_API_URL } = import.meta.env;

const DEV = MODE === 'development';
export const BASE_API_URL = DEV ? `${VITE_API_URL}/api` : `/api`;

export const publicApi = axios.create({
  baseURL: BASE_API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const privateApi = axios.create({
  baseURL: BASE_API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const multiFormApi = axios.create({
  baseURL: BASE_API_URL,
  headers: {
    'Content-Type': 'multipart/form-data',
  },
});
// 토큰 만료시간을 확인한다
// 토큰 만료시간 직전이거나 만료시간이 지났을때, 토큰을 재발급 받는다

privateApi.interceptors.request.use(
  (config) => {
    // const accessToken = useRecoilValue(accessTokenState);
    // const token = localStorage.getItem('accessToken');
    const token = getAccessToken();
    console.log(token, '토큰!!!!!!!!!!!!!!!!!!!!');

    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },

  (error) => {
    return Promise.reject(error);
  }
);

privateApi.interceptors.response.use(
  (response) => response,
  async (error) => {
    console.log(error.response, '페쳐 error.response');

    // if (error.response.status === 401) {
    //   const token = getRefreshToken();
    //   if (!token) {
    //     clearLoginInfo();
    //     return;
    //   }

    //   try {
    //     const { accessToken, refreshToken } = await fetchRefresh(token);
    //     const payload = jwtDecode(accessToken);
    //     // TODO 백에서 refresh에 변동이 있을수있음
    //     setAccessToken(accessToken);
    //     setRefreshToken(refreshToken);
    //     setUserInfo(JSON.stringify(payload));

    //     error.config.headers['Authorization'] = `Bearer ${accessToken}`;
    //     return privateApi.request(error.config);
    //   } catch (error) {
    //     clearLoginInfo();
    //     return;
    //   }
    // }
    // 에러처리
    return Promise.reject(error);
  }
);

multiFormApi.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('accessToken');

    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },

  (error) => {
    return Promise.reject(error);
  }
);
