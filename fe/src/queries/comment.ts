import {
  useInfiniteQuery,
  useMutation,
  useQueryClient,
} from '@tanstack/react-query';
import {
  deleteComment,
  getAllComments,
  postNewComment,
  putEditComment,
} from 'api/comment/comment';
import 'api/feed/feed';
import { AxiosError } from 'axios';
import { useMemo } from 'react';
import { useToast } from 'recoil/toast/useToast';
import { QUERY_KEY } from 'constants/queryKey';

export const useGetComments = (id: string) => {
  const {
    data,
    hasNextPage,
    status,
    isFetchingNextPage,
    fetchNextPage,
    refetch,
  } = useInfiniteQuery({
    queryKey: [QUERY_KEY.comments, id],
    queryFn: ({ pageParam = 0 }) => getAllComments(pageParam, 10, id),
    getNextPageParam: (lastPage) => {
      console.log('lastPage.empty', lastPage.empty);

      return lastPage.empty ? undefined : lastPage.number + 1;
    },
  });

  console.log('hasNextPage', hasNextPage);

  const allComments = useMemo(() => {
    return data?.pages.flatMap((page) => page.content) ?? [];
  }, [data]);
  // sort 반대로 해서 가져오기
  return {
    comments: allComments,
    hasNextPage,
    status,
    isFetchingNextPage,
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
    // 바디가 없을 때
    // 댓글이 없을 때
    // 댓글이 비여 있으면
    // 댓글이 공백 일때
    // 댓글이 200자를 넘을 때
    // 피드가 존재하지 않을 때
    // 피드 아이디가 없을 때

    onError: (error: AxiosError<ErrorResponse>) => {
      const errorData = error?.response?.data;

      if (errorData && errorData.code === 'c005') {
        toast.error('댓글200자까지 입력할 수 있어요');
      } else if (errorData && errorData.code === 'g001') {
        toast.error('댓글을 입력해주세요');
      } else {
        errorData && toast.error(errorData.message);
      }
    },
  });
};

export const usePutComment = () => {
  // 쿼리키 배열에 id: string 넣는거 고려(인피니트 쿼리때문에)
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (args: EditCommentArgs) => putEditComment(args.id, args.body),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.comments]);
    },

    onError: (error: AxiosError<ErrorResponse>) => {
      const errorData = error?.response?.data;

      if (errorData && errorData.code === 'c005') {
        toast.error('댓글200자까지 입력할 수 있어요');
      } else if (errorData && errorData.code === 'g001') {
        toast.error('댓글을 입력해주세요');
      } else {
        errorData && toast.error(errorData.message);
      }
    },
  });
};

export const useDeleteComment = () => {
  const toast = useToast();

  return useMutation({
    mutationFn: (id: string) => deleteComment(id),
    onSuccess: () => {},

    onError: (error: AxiosError<ErrorResponse>) => {
      console.log('delete feed error: ', error);
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};
