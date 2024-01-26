import { publicApi } from 'service/axios/fetcher';
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

export const getProfileCollections = async (
  page = 0,
  size = 10,
  memberId: string,
  sort?: string
) => {
  const { data } = await publicApi.get(
    END_POINT.memberCollections(memberId, sort),
    {
      params: { page, size },
    }
  );
  return data;
};

export const getDetailCollection = async (id: string) => {
  const { data } = await publicApi.get(END_POINT.collection(id));
  return data;
};
