import {
  getAccessToken,
  getRefreshToken,
  getUserInfo,
} from 'utils/localStorage';

export const useAuthState = () => {
  const isLogin = !!getAccessToken() && !!getRefreshToken() && !!getUserInfo();
  const userInfo = getUserInfo();

  return { isLogin, userInfo };
};
