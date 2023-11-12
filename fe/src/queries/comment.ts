import { useMutation, useQueryClient } from '@tanstack/react-query';
import {
  deleteComment,
  postNewComment,
  putEditComment,
} from 'api/comment/comment';
import 'api/feed/feed';
import { QUERY_KEY } from 'constants/queryKey';

// export const useGetComments = (id: string) =>
//   useQuery({
//     queryKey: [QUERY_KEY.comments, id],
//     queryFn: () => getFeedDetail(id),
//   });

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
