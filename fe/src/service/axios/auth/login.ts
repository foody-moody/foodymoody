import { privateApi, publicApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const fetchLogin = async (body: LoginBody) => {
  const { data } = await publicApi.post(END_POINT.login, body);
  return data;
};

export const fetchLogout = async () => {
  const { data } = await privateApi.post(END_POINT.logout);
  return data;
};

export const fetchRegister = async (body: RegisterBody) => {
  const { data } = await publicApi.post(END_POINT.register, body);
  return data;
};

export const fetchUnRegister = async () => {
  const { data } = await privateApi.delete(END_POINT.member());
  return data;
};

export const fetchRefresh = async (refreshToken: string) => {
  const { data } = await publicApi.post(END_POINT.refresh, { refreshToken });
  return data;
};
