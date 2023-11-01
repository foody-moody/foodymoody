import { privateApi } from 'api/fetcher';
import { END_POINT } from 'constants/endpoint';

export const postNewFeed = async (body: NewFeedBody) => {
  const { data } = await privateApi.post(END_POINT.newFeed, body);
  return data;
};

export const putEditFeed = async (id: string, body: NewFeedBody) => {
  const { data } = await privateApi.put(END_POINT.newFeed + `/${id}`, body);
  return data;
};

export const deleteFeed = async (id: string) => {
  const { data } = await privateApi.delete(END_POINT.newFeed + `/${id}`);
  return data;
};
