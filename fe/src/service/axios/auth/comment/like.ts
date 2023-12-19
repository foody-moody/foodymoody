import { privateApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const postCommentLikeStatus = async (id: string) => {
  const { data } = await privateApi.post(END_POINT.commentLike(id));
  return data;
};

export const deleteCommentLikeStatus = async (id: string) => {
  const { data } = await privateApi.delete(END_POINT.commentLike(id));
  return data;
};

export const postReplyLikeStatus = async ({
  commentId,
  replyId,
}: ReplyLike) => {
  const { data } = await privateApi.post(
    END_POINT.replyLike({ commentId, replyId })
  );
  return data;
};

export const deleteReplyLikeStatus = async ({
  commentId,
  replyId,
}: ReplyLike) => {
  const { data } = await privateApi.delete(
    END_POINT.replyLike({ commentId, replyId })
  );
  return data;
};
