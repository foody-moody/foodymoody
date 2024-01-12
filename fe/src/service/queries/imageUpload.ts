import { useMutation } from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useToast } from 'recoil/toast/useToast';
import { postFeedImage } from 'service/axios/feed/imageUpload';
import { postUserImage } from 'service/axios/profile/imageUpload';

export const usePostImage = (type: 'feed' | 'user') => {
  const toast = useToast();

  return useMutation({
    mutationFn: type === 'feed' ? postFeedImage : postUserImage,

    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;
      errorData && toast.error(errorData.message);
    },
  });
};
