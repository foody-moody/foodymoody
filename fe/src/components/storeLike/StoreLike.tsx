import { useCallback, useMemo } from 'react';
import { useNavigate } from 'react-router-dom';
import { useToast } from 'recoil/toast/useToast';
import { useDeleteStoreLike, usePostStoreLike } from 'service/queries/like';
import { HeartFillIcon } from 'components/common/icon/icons';
import { HeartNoBgIcon } from 'components/common/icon/icons';
import { useAuthState } from 'hooks/auth/useAuth';
import { PATH } from 'constants/path';

type Props = {
  storeId: string;
  liked?: boolean;
  likeCount?: number;
};

export const StoreLike = ({ storeId, liked, likeCount }: Props) => {
  const navigate = useNavigate();
  const { isLogin } = useAuthState();
  const toast = useToast();
  const { mutate: likeMutate } = usePostStoreLike(storeId);
  const { mutate: unLikeMutate } = useDeleteStoreLike(storeId);

  const Icon = useMemo(() => {
    if (isLogin) {
      return liked ? HeartFillIcon : HeartNoBgIcon;
    }
    return HeartFillIcon;
  }, [isLogin, liked]);

  const handleLike = () => {
    if (!isLogin) {
      navigate(PATH.LOGIN);
      toast.noti('로그인이 필요한 서비스입니다');
      return;
    } else {
      return liked ? unLikeMutate() : likeMutate();
    }
  };

  return (
    <>
      <div>{likeCount}</div>
      <Icon onClick={handleLike} />
    </>
  );
};
