import { privateApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const postLikeStatus = async (body: LikeBody) => {
  const { data } = await privateApi.post(END_POINT.like, body);
  return data;
};
