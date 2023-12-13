import { useMutation, useQueryClient } from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useToast } from 'recoil/toast/useToast';
import {
  deleteCommentLikeStatus,
  postCommentLikeStatus,
} from 'service/axios/auth/comment/like';
import 'service/axios/feed/feed';
import { deleteLikeStatus, postLikeStatus } from 'service/axios/like/like';
import { QUERY_KEY } from 'service/constants/queryKey';

// export const usePostLike = (urlParam?: string) => {
//   const queryClient = useQueryClient();
//   const toast = useToast();

//   return useMutation({
//     mutationFn: (body: LikeBody) => postLikeStatus(body),
//     onMutate: async (body: LikeBody) => {
//       console.log(urlParam); // TODO urlParam이 없으면 mainFeed, 있으면 feedDetail => 갱신해야할 쿼리가 다름

//       await queryClient.cancelQueries([QUERY_KEY.feedDetail, body.feedId]);

//       const previousData = queryClient.getQueryData<MainFeed | undefined>([
//         QUERY_KEY.feedDetail,
//         body.feedId,
//       ]);

//       if (previousData) {
//         const updatedData = {
//           ...previousData,
//           isLiked: !previousData.isLiked,
//           likeCount: previousData.isLiked
//             ? previousData.likeCount - 1
//             : previousData.likeCount + 1,
//         };

//         queryClient.setQueryData(
//           [QUERY_KEY.feedDetail, body.feedId],
//           updatedData
//         );
//       }

//       return { previousData };
//     },
//     onError: (error: AxiosError<CustomErrorResponse>) => {
//       const errorData = error?.response?.data;

//       errorData && toast.error(errorData.message);
//     },
//   });
// };

export const usePostFeedLike = (urlParam?: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (id: string) => postLikeStatus(id),
    onMutate: async (id: string) => {
      console.log(urlParam); // TODO urlParam이 없으면 mainFeed, 있으면 feedDetail => 갱신해야할 쿼리가 다름
      await queryClient.cancelQueries([QUERY_KEY.feedDetail, id]);

      const previousData = queryClient.getQueryData<MainFeed | undefined>([
        QUERY_KEY.feedDetail,
        id,
      ]);

      queryClient.setQueryData(
        [QUERY_KEY.feedDetail, id],
        (oldData: MainFeed | undefined) => {
          if (oldData) {
            return {
              ...oldData,
              isLiked: true,
              likeCount: oldData.likeCount + 1,
            };
          }
          return oldData;
        }
      );

      return { previousData };
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
    onMutate: async (id: string) => {
      console.log(urlParam);
      await queryClient.cancelQueries([QUERY_KEY.feedDetail, id]);

      const previousData = queryClient.getQueryData<MainFeed | undefined>([
        QUERY_KEY.feedDetail,
        id,
      ]);

      queryClient.setQueryData(
        [QUERY_KEY.feedDetail, id],
        (oldData: MainFeed | undefined) => {
          if (oldData) {
            return {
              ...oldData,
              likeCount: oldData.likeCount > 0 ? oldData.likeCount - 1 : 0,
              isLiked: false,
            };
          }
          return oldData;
        }
      );

      return { previousData };
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const usePostCommentLike = ({ isReply }: { isReply: boolean }) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (id: string) => postCommentLikeStatus(id),
    onMutate: async (id: string) => {
      isReply
        ? await queryClient.cancelQueries([QUERY_KEY.replies, id])
        : await queryClient.cancelQueries([QUERY_KEY.comments, id]);

      const previousData = queryClient.getQueryData([QUERY_KEY.feedDetail, id]);

      queryClient.setQueryData(
        [QUERY_KEY.feedDetail, id],
        (oldData: CommentItemType | ReplyItemType | undefined) => {
          if (oldData) {
            return {
              ...oldData,
              heartCount: oldData.heartCount + 1,
              hearted: true,
            };
          }
          return oldData;
        }
      );

      return { previousData };
    },
    onSuccess: (id: string) => {
      isReply
        ? queryClient.invalidateQueries([QUERY_KEY.replies, id])
        : queryClient.cancelQueries([QUERY_KEY.comments, id]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteCommentLike = ({ isReply }: { isReply: boolean }) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (id: string) => deleteCommentLikeStatus(id),
    onMutate: async (id: string) => {
      isReply
        ? await queryClient.cancelQueries([QUERY_KEY.replies, id])
        : await queryClient.cancelQueries([QUERY_KEY.comments]);

      const previousData = queryClient.getQueryData([QUERY_KEY.feedDetail, id]);

      queryClient.setQueryData(
        [QUERY_KEY.feedDetail, id],
        (oldData: CommentItemType | ReplyItemType | undefined) => {
          if (oldData) {
            return {
              ...oldData,
              heartCount: oldData.heartCount > 0 ? oldData.heartCount - 1 : 0,
              hearted: false,
            };
          }
          return oldData;
        }
      );

      return { previousData };
    },
    onSuccess: (id: string) => {
      isReply
        ? queryClient.invalidateQueries([QUERY_KEY.replies, id])
        : queryClient.cancelQueries([QUERY_KEY.comments, id]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};
