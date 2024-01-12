import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useToast } from 'recoil/toast/useToast';
import {
  getNicknameDuplicate,
  getProfile,
  patchEditProfile,
  patchProfileImage,
} from 'service/axios/profile/profile';
import { QUERY_KEY } from 'service/constants/queryKey';

export const useGetProfile = (memberId?: string) => {
  return useQuery<ProfileMemberInfo>({
    queryKey: [QUERY_KEY.profile, memberId],
    queryFn: () => getProfile(memberId),
    suspense: true,
  });
};

export const useEditProfileImage = (memberId: string) => {
  const queryClient = useQueryClient();

  const toast = useToast();

  return useMutation({
    mutationFn: (body: ProfileImageBody) => patchProfileImage(memberId, body),
    onSuccess: () => {
      toast.success('프로필 이미지를 수정했습니다.');
      queryClient.invalidateQueries([QUERY_KEY.profile, memberId]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;
      console.log('useEditProfileImage error: ', error);

      errorData && toast.error(errorData.message);
    },
  });
};

export const useEditProfile = (memberId: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (body: ProfileEditBody) => patchEditProfile(memberId, body),
    onSuccess: () => {
      toast.success('프로필을 수정했습니다.');
      queryClient.invalidateQueries([QUERY_KEY.profile, memberId]);

      // 어디로 이동까지 시켜야할지?
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;
      console.log('useEditProfile error: ', error);

      errorData && toast.error(errorData.message);
    },
  });
};

export const useGetNicknameDuplicate = (nickName: string) => {
  return useQuery({
    queryKey: [QUERY_KEY.nickname, nickName],
    queryFn: () => getNicknameDuplicate(nickName),
    enabled: false,
  });
};
