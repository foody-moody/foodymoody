import axios from 'axios';

const { MODE, VITE_API_URL } = import.meta.env;

const DEV = MODE === 'development';
const LOCALHOST_API_URL = 'http://localhost:5173';
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

    // TODO 리프레시 토큰 및 에러

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
