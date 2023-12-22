import { useMutation, useQueryClient } from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useToast } from 'recoil/toast/useToast';
import { deleteFollow, postFollow } from 'service/axios/follow/follow';
import { QUERY_KEY } from 'service/constants/queryKey';

export const usePostFollow = (memberId: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: () => postFollow(memberId),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.profile, memberId]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteFollow = (memberId: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: () => deleteFollow(memberId),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.profile, memberId]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};
