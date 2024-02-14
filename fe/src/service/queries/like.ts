import { useMutation, useQueryClient } from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useToast } from 'recoil/toast/useToast';
import {
  deleteCommentLikeStatus,
  deleteReplyLikeStatus,
  postCommentLikeStatus,
  postReplyLikeStatus,
} from 'service/axios/auth/comment/like';
import 'service/axios/feed/feed';
import { deleteLikeStatus, postLikeStatus } from 'service/axios/like/like';
import { QUERY_KEY } from 'service/constants/queryKey';

export const usePostFeedLike = (feedId?: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (id: string) => postLikeStatus(id),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.feedDetail, feedId]);
      queryClient.invalidateQueries([QUERY_KEY.allFeeds]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteFeedLike = (feedId?: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (id: string) => deleteLikeStatus(id),

    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.feedDetail, feedId]);
      queryClient.invalidateQueries([QUERY_KEY.allFeeds]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const usePostCommentLike = (feedId?: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (comment: string) => postCommentLikeStatus(comment, feedId),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.comments]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteCommentLike = (feedId?: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (commentId: string) =>
      deleteCommentLikeStatus(commentId, feedId),

    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.comments]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const usePostReplyLike = (callbackFn?: () => void, feedId?: string) => {
  const toast = useToast();

  return useMutation({
    mutationFn: ({ commentId, replyId }: ReplyLike) =>
      postReplyLikeStatus(
        {
          commentId,
          replyId,
        },
        feedId
      ),
    onSuccess: () => {
      callbackFn?.();
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteReplyLike = (
  callbackFn?: () => void,
  feedId?: string
) => {
  const toast = useToast();

  return useMutation({
    mutationFn: ({ commentId, replyId }: ReplyLike) =>
      deleteReplyLikeStatus(
        {
          commentId,
          replyId,
        },
        feedId
      ),
    onSuccess: () => {
      callbackFn?.();
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};
