import { privateApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const postCommentLikeStatus = async (
  commentId: string,
  feedId?: string
) => {
  const { data } = await privateApi.post(
    END_POINT.commentLike(commentId, feedId)
  );
  return data;
};

export const deleteCommentLikeStatus = async (
  commentId: string,
  feedId?: string
) => {
  const { data } = await privateApi.delete(
    END_POINT.commentLike(commentId, feedId)
  );
  return data;
};

export const postReplyLikeStatus = async (
  { commentId, replyId }: ReplyLike,
  feedId?: string
) => {
  const { data } = await privateApi.post(
    END_POINT.replyLike({ commentId, replyId }, feedId)
  );
  return data;
};

export const deleteReplyLikeStatus = async (
  { commentId, replyId }: ReplyLike,
  feedId?: string
) => {
  const { data } = await privateApi.delete(
    END_POINT.replyLike({ commentId, replyId }, feedId)
  );
  return data;
};
