import { publicApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const getAllCollections = async (
  page = 0,
  size = 10,
  sort: string = 'createdAt'
) => {
  const { data } = await publicApi.get(END_POINT.collection(undefined, sort), {
    params: { page, size },
  });
  return data;
};
