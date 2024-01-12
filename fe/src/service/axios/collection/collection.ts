import { publicApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const getAllCollections = async (page = 0, size = 10) => {
  const { data } = await publicApi.get(END_POINT.collection(), {
    params: { page, size },
  });
  return data;
};
