import { privateApi, publicApi } from 'service/axios/fetcher';
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

export const getDetailCollection = async (id: string) => {
  const { data } = await publicApi.get(END_POINT.collection(id));
  return data;
};

export const getUserCollectionTitle = async () => {
  const { data } = await privateApi.get('/members/me/collections/titles');
  return data;
};

export const addUserCollection = async (collectionForm: CollectionForm) => {
  const { data } = await privateApi.post('/feed_collections', collectionForm);
  return data;
};
