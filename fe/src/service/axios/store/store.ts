import { END_POINT } from 'service/constants/endpoint';
import { privateApi, publicApi } from '../fetcher';

export const getSearchStores = async (keyword: string) => {
  const encodedKeyword = encodeURIComponent(keyword);
  const { data } = await privateApi.get(
    END_POINT.store(undefined, encodedKeyword)
  );
  return data;
};

export const getStoreDetail = async (storeId: string) => {
  const { data } = await publicApi.get(END_POINT.store(storeId));
  return data;
};
