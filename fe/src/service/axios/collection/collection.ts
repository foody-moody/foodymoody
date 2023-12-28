import { privateApi, publicApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

// 이거 왜 프라이빗인지?
export const getAllCollections = async (page = 0, size = 10) => {
  const { data } = await privateApi.get(END_POINT.collection(), {
    params: { page, size },
  });
  return data;
};
