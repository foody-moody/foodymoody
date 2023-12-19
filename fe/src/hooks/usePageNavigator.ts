import { useNavigate } from 'react-router-dom';
import { PATH } from 'constants/path';

export const usePageNavigator = () => {
  const navigate = useNavigate();

  return {
    navigateToHome: () => navigate(PATH.HOME),
    navigateToProfile: () => navigate(PATH.PROFILE),
    navigateToCollection: () => navigate(PATH.COLLECTION),
    navigateToNoti: () => navigate(PATH.NOTI),
    navigateToLogin: () => navigate(PATH.LOGIN),
    navigateToRegister: () => navigate(PATH.REGISTER),
    navigateToSearch: () => navigate(PATH.SEARCH),
    navigateToProfileEdit: () => navigate(PATH.PROFILE_EDIT),
    navigateToSetting: () => navigate(PATH.SETTING),
    navigateToPassword: () => navigate(PATH.PASSWORD),
    navigateToAccount: () => navigate(PATH.ACCOUNT),
    navigateToPath: (path: string) => navigate(path),

    /* 잠시 생성 */
    navigateToNewFeed: () => navigate(PATH.NEW_FEED),
    navigateToDetailFeed: () => navigate(PATH.DETAIL_FEED),
  };
};
