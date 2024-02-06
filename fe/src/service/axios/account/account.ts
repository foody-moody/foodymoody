import { privateApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const putEditPassword = async (memberId: string, body: PasswordBody) => {
  const { data } = await privateApi.put(END_POINT.password(memberId), body);
  return data;
};

export const deleteAccount = async () => {
  const { data } = await privateApi.delete(END_POINT.member());
  return data;
};
