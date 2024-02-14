import {
  useInfiniteQuery,
  useMutation,
  useQueryClient,
} from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useMemo } from 'react';
import { useToast } from 'recoil/toast/useToast';
import {
  deleteFollow,
  getFollowers,
  getFollowings,
  postFollow,
} from 'service/axios/follow/follow';
import { QUERY_KEY } from 'service/constants/queryKey';

export const useGetFollows = (
  followType: 'followers' | 'followings',
  memberId?: string
) => {
  const { data, hasNextPage, status, isLoading, fetchNextPage, refetch } =
    useInfiniteQuery({
      queryKey:
        followType === 'followers'
          ? [QUERY_KEY.followers]
          : [QUERY_KEY.followings],
      queryFn:
        followType === 'followers'
          ? ({ pageParam = 0 }) => getFollowers(memberId, pageParam)
          : ({ pageParam = 0 }) => getFollowings(memberId, pageParam),
      getNextPageParam: (lastPage) => {
        return lastPage.last ? undefined : lastPage.number + 1;
      },
      suspense: true,
      enabled: !!memberId,
    });

  const allFollowList = useMemo(() => {
    return data?.pages.flatMap((page) => page.content) ?? [];
  }, [data]);

  return {
    followList: allFollowList,
    hasNextPage,
    status,
    isLoading,
    fetchNextPage,
    refetch,
  };
};

export const usePostFollow = (memberId?: string, queryKey?: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: () => postFollow(memberId),
    onSuccess: () => {
      console.log('usePostFollow success', queryKey);
      queryKey
        ? queryClient.invalidateQueries([QUERY_KEY.profile, queryKey])
        : queryClient.invalidateQueries([QUERY_KEY.profile, memberId]);
      queryClient.invalidateQueries([QUERY_KEY.followers]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteFollow = (memberId?: string, queryKey?: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();
  return useMutation({
    mutationFn: () => deleteFollow(memberId),
    onSuccess: () => {
      console.log('deleteFollow success', queryKey);
      queryKey
        ? queryClient.invalidateQueries([QUERY_KEY.profile, queryKey])
        : queryClient.invalidateQueries([QUERY_KEY.profile, memberId]);
      queryClient.invalidateQueries([QUERY_KEY.followings]);
      queryClient.invalidateQueries([QUERY_KEY.followers]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};
