import axios from 'axios';
import { getAccessToken, getUserInfo } from 'utils/localStorage';

const { MODE, VITE_API_URL } = import.meta.env;

// const DEV = MODE === 'development';
// export const BASE_API_URL = DEV ? `${VITE_API_URL}/api` : `/api`;
export const BASE_API_URL = `${VITE_API_URL}/api`;

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

publicApi.interceptors.request.use(
  (config) => {
    const token = getAccessToken();
    const userInfo = getUserInfo();

    const checkTokenExpiry = (userInfo: UserInfoType) => {
      const { exp } = userInfo;
      const now = Date.now() / 1000;
      return exp < now;
    };

    if (token) {
      if (!checkTokenExpiry(userInfo)) {
        config.headers['Authorization'] = `Bearer ${token}`;
      } else {
        console.log('토큰 만료');
        //TODO 리프레쉬요청 로직을 여기도 추가해야하는지 보류
      }
    }
    return config;
  },

  (error) => {
    console.log('publicApi error', error);

    return Promise.reject(error);
  }
);

privateApi.interceptors.request.use(
  (config) => {
    const token = getAccessToken();

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
