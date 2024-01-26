import {
  useInfiniteQuery,
  useMutation,
  useQueryClient,
} from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useMemo } from 'react';
import { useToast } from 'recoil/toast/useToast';
import { getAllReplies, postNewReply } from 'service/axios/auth/comment/reply';
import 'service/axios/feed/feed';
import { QUERY_KEY } from 'service/constants/queryKey';

export const useGetReplies = (commentId: string) => {
  console.log('replies commentId', commentId);

  const { data, hasNextPage, isFetching, fetchNextPage, refetch } =
    useInfiniteQuery({
      queryKey: [QUERY_KEY.replies, commentId],
      queryFn: ({ pageParam = 0 }) => getAllReplies(pageParam, 10, commentId),
      getNextPageParam: (lastPage) => {
        return lastPage.last ? undefined : lastPage.number + 1;
      },
      enabled: false,
    });

  const allReplies = useMemo(() => {
    return data?.pages.flatMap((page) => page.content) ?? [];
  }, [data]);

  return {
    replies: allReplies,
    hasNextPage,
    isFetching,
    fetchNextPage,
    refetch,
  };
};

export const usePostReply = (id: string, callbackFn?: () => void) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (body: Omit<NewCommentBody, 'feedId'>) =>
      postNewReply(body, id),
    onSuccess: () => {
      callbackFn?.();
      queryClient.invalidateQueries([QUERY_KEY.comments]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      if (errorData && errorData.code === 'g001') {
        toast.error('댓글을 입력해주세요');
      } else {
        errorData && toast.error(errorData.message);
      }
    },
  });
};
