import { privateApi, publicApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const getAllComments = async (page = 0, size = 10, feedId: string) => {
  const { data } = await publicApi.get(END_POINT.comment(feedId), {
    params: { page, size },
  });
  return data;
};

export const postNewComment = async (body: NewCommentBody, feedId?: string) => {
  const { data } = await privateApi.post(END_POINT.comment(feedId), body);
  return data;
};

export const putEditComment = async (
  commentId: string,
  body: EditCommentArgs['body'],
  feedId?: string
) => {
  const { data } = await privateApi.put(
    END_POINT.comment(feedId, commentId),
    body
  );
  return data;
};

export const deleteComment = async (commentId: string, feedId?: string) => {
  const { data } = await privateApi.delete(
    END_POINT.comment(feedId, commentId)
  );
  return data;
};
