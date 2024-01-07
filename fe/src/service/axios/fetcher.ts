import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
import { useNavigate } from 'react-router-dom';
import {
  clearLoginInfo,
  getRefreshToken,
  setAccessToken,
  setRefreshToken,
  setUserInfo,
} from 'utils/localStorage';
import { fetchRefresh } from './auth/login';
import { PATH } from 'constants/path';

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

privateApi.interceptors.request.use(
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

privateApi.interceptors.response.use(
  (response) => response,
  async (error) => {
    console.log(error.response, '페쳐 error.response');
    if (error.response.status === 401) {
      const token = getRefreshToken();
      if (!token) {
        return Promise.reject(error);
      }

      const { accessToken, refreshToken } = await fetchRefresh(token);
      const payload = jwtDecode(accessToken);

      setAccessToken(accessToken);
      setRefreshToken(refreshToken);
      setUserInfo(JSON.stringify(payload));
      console.log('리프레시 토큰 다시받아서 요청 보낼거임');

      error.config.headers['Authorization'] = `Bearer ${accessToken}`;
      return privateApi.request(error.config);
    } else if (error.response.data.code === 'a002') {
      console.log('리프레시 토큰 만료~~ 에러 ');
      //리프레쉬토큰 만료
      clearLoginInfo();

      const navigate = useNavigate();
      navigate(PATH.HOME, { replace: true });
    }
    // TODO 리프레시 토큰 및 에러
    // 에러바운더리 캐치
    console.log('그냥 에러 에러바운더리 ');

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
