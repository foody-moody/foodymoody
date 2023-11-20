import { privateApi, publicApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const getAllFeeds = async (page = 0, size = 10) => {
  const { data } = await publicApi.get(END_POINT.feed(), {
    params: { page, size },
  });
  return data;
};

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

export const deleteFeed = async (id: string) => {
  const { data } = await privateApi.delete(END_POINT.feed(id));
  return data;
};
