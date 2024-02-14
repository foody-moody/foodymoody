import { useMutation } from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useNavigate } from 'react-router-dom';
import { useToast } from 'recoil/toast/useToast';
import { deleteAccount, putEditPassword } from 'service/axios/account/account';
import { clearLoginInfo } from 'utils/localStorage';
import { PATH } from 'constants/path';

export const usePutPassword = () => {
  const toast = useToast();
  const navigate = useNavigate();

  return useMutation({
    mutationFn: (body: PasswordBody) => putEditPassword(body),
    onSuccess: () => {
      toast.success('비밀번호를 변경했습니다.');
      navigate(PATH.SETTING);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteAccount = () => {
  const toast = useToast();
  const navigate = useNavigate();

  return useMutation({
    mutationFn: () => deleteAccount(),
    onSuccess: () => {
      toast.success('성공적으로 탈퇴 되었습니다.');
      clearLoginInfo();
      navigate(PATH.HOME, { replace: true });
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;

      errorData && toast.error(errorData.message);
    },
  });
};
