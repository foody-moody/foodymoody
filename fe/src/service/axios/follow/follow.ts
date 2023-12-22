import { privateApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const postFollow = async (id: string) => {
  const { data } = await privateApi.post(END_POINT.follow(id));
  return data;
};

export const deleteFollow = async (id: string) => {
  const { data } = await privateApi.delete(END_POINT.follow(id));
  return data;
};
