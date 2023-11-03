import { privateApi, publicApi } from 'api/fetcher';
import { END_POINT } from 'constants/endpoint';

export const getFeedDetail = async (id: string) => {
  const { data } = await publicApi.get(END_POINT.feed(id));
  return data;
};

export const postNewFeed = async (body: NewFeedBody) => {
  const { data } = await privateApi.post(END_POINT.feed(), body);
  return data;
};

export const putEditFeed = async (id: string, body: NewFeedBody) => {
  const { data } = await privateApi.put(END_POINT.feed(id), body);
  return data;
};
// 이런식으로 하나씩 id붙이는게 나은지
export const deleteFeed = async (id: string) => {
  const { data } = await privateApi.delete(END_POINT.feed(id));
  return data;
};
