import { privateApi, publicApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const getAllReplies = async (
  page = 0,
  size = 10,
  commentId: string,
  feedId?: string
) => {
  const { data } = await publicApi.get(END_POINT.reply(feedId, commentId), {
    params: { page, size },
  });
  return data;
};

export const postNewReply = async (
  body: Omit<NewCommentBody, 'feedId'>,
  feedId?: string,
  commentId?: string
) => {
  const { data } = await privateApi.post(
    END_POINT.comment(feedId, commentId),
    body
  );
  return data;
};
