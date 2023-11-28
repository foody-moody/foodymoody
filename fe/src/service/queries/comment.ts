import {
  useInfiniteQuery,
  useMutation,
  useQueryClient,
} from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useMemo } from 'react';
import { useToast } from 'recoil/toast/useToast';
import {
  deleteComment,
  getAllComments,
  postNewComment,
  putEditComment,
} from 'service/axios/auth/comment/comment';
import 'service/axios/feed/feed';
import { QUERY_KEY } from 'service/constants/queryKey';

export const useGetComments = (id: string) => {
  const { data, hasNextPage, status, isLoading, fetchNextPage, refetch } =
    useInfiniteQuery({
      queryKey: [QUERY_KEY.comments, id],
      queryFn: ({ pageParam = 0 }) => getAllComments(pageParam, 10, id),
      getNextPageParam: (lastPage) => {
        return lastPage.last ? undefined : lastPage.number + 1;
      },
    });

  const allComments = useMemo(() => {
    return data?.pages.flatMap((page) => page.content) ?? [];
  }, [data]);

  return {
    comments: allComments,
    hasNextPage,
    status,
    isLoading,
    fetchNextPage,
    refetch,
  };
};

export const usePostComment = (id: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (body: NewCommentBody) => postNewComment(body),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.comments, id]);
    },
    onError: (error: AxiosError<ErrorResponse>) => {
      const errorData = error?.response?.data;

      if (errorData && errorData.code === 'g001') {
        toast.error('댓글을 입력해주세요');
      } else {
        errorData && toast.error(errorData.message);
      }
    },
  });
};

export const usePutComment = () => {
  // export const usePutComment = (id: string) => { 피드 id
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (args: EditCommentArgs) => putEditComment(args.id, args.body),
    onSuccess: () => {
      // queryClient.invalidateQueries([QUERY_KEY.comments, id]);
      queryClient.invalidateQueries([QUERY_KEY.comments]);
    },
    onError: (error: AxiosError<ErrorResponse>) => {
      const errorData = error?.response?.data;

      if (errorData && errorData.code === 'g001') {
        toast.error('댓글을 입력해주세요');
      } else {
        errorData && toast.error(errorData.message);
      }
    },
  });
};

export const useDeleteComment = () => {
  const toast = useToast();
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (id: string) => deleteComment(id),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.comments]);
    },
    onError: (error: AxiosError<ErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};
