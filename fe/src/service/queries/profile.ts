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

export const useGetProfile = (id?: string) =>
  useQuery<ProfileMemberInfo>({
    queryKey: [QUERY_KEY.profile, id],
    queryFn: () => getProfile(id),
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

export const useEditProfile = (memberId: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (body: ProfileEditBody) => patchEditProfile(memberId, body),
    onSuccess: () => {
      toast.success('프로필을 수정했습니다.');
      queryClient.invalidateQueries([QUERY_KEY.profile, memberId]); //디테일 페이지 데이터를 다시 갱신시키고 바뀐 imageId 싱크를 맞추기 위함, 이렇게해서 imageId는 항상 이전값과 같으므로 null로 보낼수있음, 이미지는 에딧 하자마자 뮤테이트 됨.

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
