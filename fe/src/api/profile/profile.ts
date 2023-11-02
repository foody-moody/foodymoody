import { privateApi } from 'api/fetcher';
import { END_POINT } from 'constants/endpoint';

export const getProfile = async (memberId?: string) => {
  const { data } = await privateApi.get(END_POINT.member(memberId));
  return data;
};
