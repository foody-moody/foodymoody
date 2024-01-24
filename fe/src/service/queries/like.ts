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

export const usePostFeedLike = (urlParam?: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (id: string) => postLikeStatus(id),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.feedDetail, urlParam]);
      queryClient.invalidateQueries([QUERY_KEY.allFeeds]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteFeedLike = (urlParam?: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (id: string) => deleteLikeStatus(id),

    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.feedDetail, urlParam]);
      queryClient.invalidateQueries([QUERY_KEY.allFeeds]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const usePostCommentLike = () => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (id: string) => postCommentLikeStatus(id),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.comments]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteCommentLike = () => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (id: string) => deleteCommentLikeStatus(id),

    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.comments]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const usePostReplyLike = (callbackFn?: () => void) => {
  const toast = useToast();

  return useMutation({
    mutationFn: ({ commentId, replyId }: ReplyLike) =>
      postReplyLikeStatus({
        commentId,
        replyId,
      }),
    onSuccess: () => {
      callbackFn?.();
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteReplyLike = (callbackFn?: () => void) => {
  const toast = useToast();

  return useMutation({
    mutationFn: ({ commentId, replyId }: ReplyLike) =>
      deleteReplyLikeStatus({
        commentId,
        replyId,
      }),
    onSuccess: () => {
      callbackFn?.();
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};
