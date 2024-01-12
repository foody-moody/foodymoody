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
    navigateToProfileSetting: () => navigate(PATH.SETTING_PROFILE),
    navigateToNotiSetting: () => navigate(PATH.SETTING_NOTI),
    navigateToPassword: () => navigate(PATH.PASSWORD),
    navigateToAccount: () => navigate(PATH.ACCOUNT),
    navigateToPath: (path: string) => navigate(path),
    navigateToBack: () => navigate(-1),

    /* 잠시 생성 */
    navigateToNewFeed: () => navigate(PATH.NEW_FEED),
    navigateToDetailFeed: () => navigate(PATH.DETAIL_FEED),
  };
};
