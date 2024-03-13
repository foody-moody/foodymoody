import { privateApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const postLikeStatus = async (id: string) => {
  const { data } = await privateApi.post(END_POINT.feedLike(id));
  return data;
};

export const deleteLikeStatus = async (id: string) => {
  const { data } = await privateApi.delete(END_POINT.feedLike(id));
  return data;
};

export const addCollectionLike = async (id: string) => {
  const { data } = await privateApi.post(END_POINT.collectionLike(id));
  return data;
};

export const deleteCollectionLike = async (id: string) => {
  const { data } = await privateApi.delete(END_POINT.collectionLike(id));
  return data;
};
