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

// interface FeedLikeContext {
//   previousData: MainFeed | MainFeed[] | undefined;
// }

// export const usePostFeedLike = (urlParam?: string) => {
//   const queryClient = useQueryClient();
//   const toast = useToast();

//   return useMutation({
//     mutationFn: (id: string) => postLikeStatus(id),
//     onMutate: async (id: string) => {
//       urlParam
//         ? await queryClient.cancelQueries([QUERY_KEY.feedDetail, id])
//         : await queryClient.cancelQueries([QUERY_KEY.allFeeds]);

//       const previousData = urlParam
//         ? queryClient.getQueryData<MainFeed | undefined>([
//             QUERY_KEY.feedDetail,
//             id,
//           ])
//         : queryClient.getQueryData<MainFeed[] | undefined>([
//             QUERY_KEY.allFeeds,
//           ]);

//       queryClient.setQueryData(
//         urlParam ? [QUERY_KEY.feedDetail, id] : [QUERY_KEY.allFeeds],
//         (oldData: MainFeed | undefined) => {
//           if (oldData) {
//             return {
//               ...oldData,
//               isLiked: true,
//               likeCount: oldData.likeCount + 1,
//             };
//           }
//           return oldData;
//         }
//       );

//       return { previousData };
//     },
//     onSuccess: (id: string) => {
//       // urlParam
//       //   ? queryClient.invalidateQueries([QUERY_KEY.feedDetail, id])
//       //   : queryClient.invalidateQueries([QUERY_KEY.allFeeds]);

//       queryClient.invalidateQueries([QUERY_KEY.feedDetail, id]);
//       queryClient.invalidateQueries([QUERY_KEY.allFeeds]);
//     },
//     onError: (
//       error: AxiosError<CustomErrorResponse>,
//       context: FeedLikeContext
//     ) => {
//       const errorData = error?.response?.data;

//       errorData && toast.error(errorData.message);

//       if (context?.previousData) {
//         queryClient.setQueryData(
//           urlParam
//             ? [QUERY_KEY.feedDetail, context?.previousData]
//             : [QUERY_KEY.allFeeds],
//           context?.previousData
//         );
//       }
//     },
//   });
// };

// export const useDeleteFeedLike = (urlParam?: string) => {
//   const queryClient = useQueryClient();
//   const toast = useToast();

//   return useMutation({
//     mutationFn: (id: string) => deleteLikeStatus(id),
//     onMutate: async (id: string) => {
//       urlParam
//         ? await queryClient.cancelQueries([QUERY_KEY.feedDetail, id])
//         : await queryClient.cancelQueries([QUERY_KEY.allFeeds]);

//       const previousData = queryClient.getQueryData<MainFeed | undefined>([
//         QUERY_KEY.feedDetail,
//         id,
//       ]);

//       queryClient.setQueryData(
//         [QUERY_KEY.feedDetail, id],
//         (oldData: MainFeed | undefined) => {
//           if (oldData) {
//             return {
//               ...oldData,
//               likeCount: oldData.likeCount > 0 ? oldData.likeCount - 1 : 0,
//               isLiked: false,
//             };
//           }
//           return oldData;
//         }
//       );

//       return { previousData };
//     },
//     onSuccess: (id: string) => {
//       // urlParam
//       //   ? queryClient.invalidateQueries([QUERY_KEY.feedDetail, id])
//       //   : queryClient.invalidateQueries([QUERY_KEY.allFeeds]);
//       queryClient.invalidateQueries([QUERY_KEY.feedDetail, id]);
//       queryClient.invalidateQueries([QUERY_KEY.allFeeds]);
//     },
//     onError: (
//       error: AxiosError<CustomErrorResponse>,
//       context: FeedLikeContext
//     ) => {
//       const errorData = error?.response?.data;

//       errorData && toast.error(errorData.message);

//       if (context?.previousData) {
//         queryClient.setQueryData(
//           urlParam
//             ? [QUERY_KEY.feedDetail, context?.previousData]
//             : [QUERY_KEY.allFeeds],
//           context?.previousData
//         );
//       }
//     },
//   });
// };

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

// export const usePostCommentLike = ({ isReply }: { isReply: boolean }) => {
//   const queryClient = useQueryClient();
//   const toast = useToast();

//   return useMutation({
//     mutationFn: (id: string) => postCommentLikeStatus(id),
//     onMutate: async (id: string) => {
//       isReply
//         ? await queryClient.cancelQueries([QUERY_KEY.replies, id])
//         : await queryClient.cancelQueries([QUERY_KEY.comments, id]);

//       const previousData = queryClient.getQueryData([QUERY_KEY.feedDetail, id]);

//       queryClient.setQueryData(
//         [QUERY_KEY.feedDetail, id],
//         (oldData: CommentItemType | ReplyItemType | undefined) => {
//           if (oldData) {
//             return {
//               ...oldData,
//               heartCount: oldData.heartCount + 1,
//               hearted: true,
//             };
//           }
//           return oldData;
//         }
//       );

//       return { previousData };
//     },
//     onSuccess: (id: string) => {
//       isReply
//         ? queryClient.invalidateQueries([QUERY_KEY.replies, id])
//         : queryClient.cancelQueries([QUERY_KEY.comments, id]);
//     },
//     onError: (error: AxiosError<CustomErrorResponse>) => {
//       const errorData = error?.response?.data;

//       errorData && toast.error(errorData.message);
//     },
//   });
// };

// export const useDeleteCommentLike = ({ isReply }: { isReply: boolean }) => {
//   const queryClient = useQueryClient();
//   const toast = useToast();

//   return useMutation({
//     mutationFn: (id: string) => deleteCommentLikeStatus(id),
//     onMutate: async (id: string) => {
//       isReply
//         ? await queryClient.cancelQueries([QUERY_KEY.replies, id])
//         : await queryClient.cancelQueries([QUERY_KEY.comments]);

//       const previousData = queryClient.getQueryData([QUERY_KEY.feedDetail, id]);

//       queryClient.setQueryData(
//         [QUERY_KEY.feedDetail, id],
//         (oldData: CommentItemType | ReplyItemType | undefined) => {
//           if (oldData) {
//             return {
//               ...oldData,
//               heartCount: oldData.heartCount > 0 ? oldData.heartCount - 1 : 0,
//               hearted: false,
//             };
//           }
//           return oldData;
//         }
//       );

//       return { previousData };
//     },
//     onSuccess: (id: string) => {
//       isReply
//         ? queryClient.invalidateQueries([QUERY_KEY.replies, id])
//         : queryClient.cancelQueries([QUERY_KEY.comments, id]);
//     },
//     onError: (error: AxiosError<CustomErrorResponse>) => {
//       const errorData = error?.response?.data;

//       errorData && toast.error(errorData.message);
//     },
//   });
// };

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

export const usePostReplyLike = () => {
    // 대댓 id 넘겨주기
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: ({ commentId, replyId }: ReplyLike) =>
      postReplyLikeStatus({
        commentId,
        replyId,
      }),
    onSuccess: ({ commentId }: ReplyLike) => {
      console.log('commentId', commentId);

      queryClient.invalidateQueries([QUERY_KEY.comments]);
      queryClient.refetchQueries([QUERY_KEY.replies]); // 키값에 아이디 넣어줘야함
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteReplyLike = () => {
  // 대댓 id 넘겨주기
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: ({ commentId, replyId }: ReplyLike) =>
      deleteReplyLikeStatus({
        commentId,
        replyId,
      }),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.comments]);
      queryClient.refetchQueries([QUERY_KEY.replies]); // 키값에 아이디 넣어줘야함
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};