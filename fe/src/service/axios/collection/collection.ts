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
  const { data } = await publicApi.get(`/feed_collections/${id}`);
  return data;
};

export const addUserCollection = async (collectionForm: CollectionForm) => {
  const { data } = await privateApi.post('/feed_collections', collectionForm);

  console.log(data);

  return data;
};

// TODO. 타이틀 관련 요청 하나 더 추가되면 수정 예정
export const getUserCollectionTitle = async () => {
  const { data } = await privateApi.get('/members/me/collections/titles');
  return data;
};

export const getCollectionsWithFeedStatus = async (feedId: string | null) => {
  if (!feedId) {
    throw new Error('feedId is required');
  }

  const { data } = await privateApi.get(
    `/members/me/collections/with-feed-inclusion-status/${feedId}`
  );
  return data;
};

export const addFeedToCollection = async (
  collectionId: string,
  feedId: string
) => {
  const { data } = await privateApi.post(
    `/feed_collections/${collectionId}/feeds`,
    { feedId: feedId }
  );
  return data;
};

export const deleteFeedToCollection = async (
  collectionId: string,
  feedId: string
) => {
  const { data } = await privateApi.delete(
    `/feed_collections/${collectionId}/feeds/${feedId}`
  );
  return data;
};

export const editCollection = async (
  id: string,
  contents: {
    title: string;
    content: string;
    moodIds?: string[];
  }
) => {
  const payload = {
    ...contents,
    moodIds: contents.moodIds || [],
  };

  const { data } = await privateApi.put(`/feed_collections/${id}`, payload);
  return data;
};

export const deleteCollection = async (id: string) => {
  const { data } = await privateApi.delete(`/feed_collections/${id}`);
  return data;
};
