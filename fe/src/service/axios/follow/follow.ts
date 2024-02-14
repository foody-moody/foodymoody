import { privateApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const getFollowings = async (id?: string, page = 0, size = 10) => {
  const { data } = await privateApi.get(END_POINT.followings(id), {
    params: { page, size },
  });
  return data;
};

export const getFollowers = async (id?: string, page = 0, size = 10) => {
  const { data } = await privateApi.get(END_POINT.followers(id), {
    params: { page, size },
  });
  return data;
};

export const postFollow = async (id?: string) => {
  const { data } = await privateApi.post(END_POINT.followers(id));
  return data;
};

export const deleteFollow = async (id?: string) => {
  const { data } = await privateApi.delete(END_POINT.followers(id));
  return data;
};
