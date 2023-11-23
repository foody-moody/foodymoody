import { publicApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const getTasteMoods = async () => {
  const { data } = await publicApi.get(END_POINT.tasteMood);
  return data;
};
export const getStoreMoods = async () => {
  const { data } = await publicApi.get(END_POINT.storeMood);
  return data;
};
