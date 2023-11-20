import { publicApi } from 'api/fetcher';
import { END_POINT } from 'constants/endpoint';

export const getTasteMoods = async () => {
  const { data } = await publicApi.get(END_POINT.mood);
  return data;
};
