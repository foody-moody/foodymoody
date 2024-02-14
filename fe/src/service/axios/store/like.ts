import { privateApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const postStoreLikeStatus = async (id?: string) => {
  const { data } = await privateApi.post(END_POINT.storeLike(id));
  return data;
};

export const deleteStoreLikeStatus = async (id?: string) => {
  const { data } = await privateApi.delete(END_POINT.storeLike(id));
  return data;
};
