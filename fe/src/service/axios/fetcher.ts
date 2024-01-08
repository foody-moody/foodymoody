import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
import {
  clearLoginInfo,
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
        // TODO 에러바운더리 처리
        clearLoginInfo();
        // const navigate = useNavigate();
        // navigate(PATH.HOME, { replace: true });
        return;
      }

      try {
        const { accessToken, refreshToken } = await fetchRefresh(token);
        const payload = jwtDecode(accessToken);
        // TODO 백에서 refresh에 변동이 있을수있음
        setAccessToken(accessToken);
        setRefreshToken(refreshToken);
        setUserInfo(JSON.stringify(payload));
        console.log('리프레시 토큰 다시받아서 요청 보낼거임');

        error.config.headers['Authorization'] = `Bearer ${accessToken}`;
        return privateApi.request(error.config);
      } catch (error) {
        console.log(error, '리프레시 토큰 만료~~ 에러 ');
        //리프레쉬토큰 만료
        clearLoginInfo();
        // const navigate = useNavigate();
        // navigate(PATH.HOME, { replace: true });
        return;
      }
    }
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
