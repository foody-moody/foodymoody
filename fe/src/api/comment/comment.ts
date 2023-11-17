import { privateApi, publicApi } from 'api/fetcher';
import { END_POINT } from 'constants/endpoint';

export const getAllComments = async (page = 0, size = 10, feedId: string) => {
  const { data } = await publicApi.get(END_POINT.comment(), {
    params: { page, size, feedId },
  });
  return data;
};

export const postNewComment = async (body: NewCommentBody) => {
  const { data } = await privateApi.post(END_POINT.comment(), body);
  return data;
};

export const putEditComment = async (
  id: string,
  body: EditCommentArgs['body']
) => {
  const { data } = await privateApi.put(END_POINT.comment(id), body);
  return data;
};

export const deleteComment = async (id: string) => {
  const { data } = await privateApi.delete(END_POINT.comment(id));
  return data;
};
