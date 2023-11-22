import { privateApi, publicApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const getAllReplies = async (page = 0, size = 10, commentId: string) => {
  const { data } = await publicApi.get(END_POINT.reply(commentId), {
    params: { page, size },
  });
  return data;
};

export const postNewReply = async (
  body: Omit<NewCommentBody, 'feedId'>,
  commentId: string
) => {
  const { data } = await privateApi.post(END_POINT.comment(commentId), body);
  return data;
};
