import { useMutation, useQueryClient } from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useToast } from 'recoil/toast/useToast';
import 'service/axios/feed/feed';
import { postLikeStatus } from 'service/axios/like/like';
import { QUERY_KEY } from 'service/constants/queryKey';

export const usePostLike = (urlParam?: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (body: LikeBody) => postLikeStatus(body),
    onMutate: async (body: LikeBody) => {
      console.log(urlParam); // TODO urlParam이 없으면 mainFeed, 있으면 feedDetail => 갱신해야할 쿼리가 다름

      await queryClient.cancelQueries([QUERY_KEY.feedDetail, body.feedId]);

      const previousData = queryClient.getQueryData<MainFeed | undefined>([
        QUERY_KEY.feedDetail,
        body.feedId,
      ]);

      if (previousData) {
        const updatedData = {
          ...previousData,
          isLiked: !previousData.isLiked,
          likeCount: previousData.isLiked
            ? previousData.likeCount - 1
            : previousData.likeCount + 1,
        };

        queryClient.setQueryData(
          [QUERY_KEY.feedDetail, body.feedId],
          updatedData
        );
      }

      return { previousData };
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};
