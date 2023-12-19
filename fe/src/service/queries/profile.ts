import { useMutation, useQuery } from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useToast } from 'recoil/toast/useToast';
import {
  getNicknameDuplicate,
  getProfile,
  patchProfileImage,
} from 'service/axios/profile/profile';
import { QUERY_KEY } from 'service/constants/queryKey';

export const useGetProfile = (id?: string) =>
  useQuery<ProfileMemberInfo>({
    queryKey: [QUERY_KEY.profile],
    queryFn: () => getProfile(id),
    staleTime: Infinity,
  });

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

export const useGetNicknameDuplicate = (nickName: string) => {
  return useQuery({
    queryKey: [QUERY_KEY.nickname, nickName], //여기도 닉네임을 넣어줘야하는지?
    queryFn: () => getNicknameDuplicate(nickName),
    enabled: false,
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
