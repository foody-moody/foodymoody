import { useMutation, useQuery } from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useToast } from 'recoil/toast/useToast';
import { getProfile, patchProfileImage } from 'service/axios/profile/profile';
import { QUERY_KEY } from 'service/constants/queryKey';

export const useGetProfile = (id?: string) => {
  return useQuery({
    queryKey: [QUERY_KEY.profile],
    queryFn: async () => {
      const { myFeeds, ...memberProfileData } = await getProfile(id);
      return { myFeeds, memberProfileData };
    },
    staleTime: Infinity,
  });
};

export const useEditProfileImage = (id: string) => {
  const toast = useToast();

  return useMutation({
    mutationFn: (body: ProfileImageBody) => patchProfileImage(id, body),
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;
      console.log('useEditProfileImage error: ', error);

      errorData && toast.error(errorData.message);
    },
  });
};

export const useEditProfile = () => {};
