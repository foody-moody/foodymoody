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
import { useMemo } from 'react';
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
      lastPage.size < 10 ? null : lastPage.number + 1;
    },
  });

  const allComments = useMemo(() => {
    return data?.pages.flatMap((page) => page.content) ?? [];
  }, [data]);

  return {
    comments: allComments,
    hasNextPage,
    status,
    isFetchingNextPage,
    fetchNextPage,
    refetch,
  };
};

export const usePostComment = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (body: NewCommentBody) => postNewComment(body),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.comments]);
    },
    onError: (error) => {
      console.log('put editComment error: ', error);
      // 바디가 없을 때
      // 댓글이 없을 때
      // 댓글이 비여 있으면
      // 댓글이 공백 일때
      // 댓글이 200자를 넘을 때
      // 피드가 존재하지 않을 때
      // 피드 아이디가 없을 때
    },
  });
};

export const usePutComment = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (args: EditCommentArgs) => putEditComment(args.id, args.body),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.comments]);
    },
    onError: (error) => {
      console.log('put editComment error: ', error);
      //바디가 없을 때
      //댓글이 비여 있으면
      //댓글이 공백 일때
      //댓글이 200자를 넘을 때
      //댓글이 존재하지 않을 때
      //댓글이 이미 삭제되었을 때
    },
  });
};

export const useDeleteComment = () => {
  return useMutation({
    mutationFn: (id: string) => deleteComment(id),
    onSuccess: () => {},
    onError: (error) => {
      console.log('delete feed error: ', error);
    },
  });
};
