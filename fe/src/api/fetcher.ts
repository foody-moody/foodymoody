import axios from 'axios';

const { MODE, VITE_API_URL } = import.meta.env;

const DEV = MODE === 'development'; // 얘까지 상수화?
const LOCALHOST_API_URL = 'http://localhost:3000'; //어디에 둘까요
const BASE_API_URL = DEV ? LOCALHOST_API_URL : VITE_API_URL;

export const publicApi = axios.create({
  baseURL: `${BASE_API_URL}/api`,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const privateApi = axios.create({
  baseURL: `${BASE_API_URL}/api`,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const multiFormApi = axios.create({
  baseURL: `${BASE_API_URL}/api`,
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
    console.log(error.response.status, '페쳐 error.response.status');
    console.log(error.response, '페쳐 error.response');

    // const originalRequest = error.config;
    // if (error.response.status === 401 && !originalRequest._retry) {
    //   originalRequest._retry = true;
    //   const refreshToken = localStorage.getItem('refreshToken');
    //   const res = await publicApi.post('/auth/refresh', {
    //     refreshToken,
    //   });
    //   if (res.status === 200) {
    //     localStorage.setItem('accessToken', res.data.accessToken);
    //     return privateApi(originalRequest);
    //   }
    // }
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
