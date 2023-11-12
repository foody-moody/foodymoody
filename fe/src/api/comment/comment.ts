import { privateApi } from 'api/fetcher';
import { END_POINT } from 'constants/endpoint';

export const getAllComments = async () => {};

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
