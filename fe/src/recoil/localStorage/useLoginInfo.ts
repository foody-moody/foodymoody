import { useSetRecoilState } from 'recoil';
import { clearLoginInfo } from 'utils/localStorage';
import { accessTokenState, refreshTokenState, userInfoState } from './atom';

export const useClearLoginInfo = () => {
  const setAccessToken = useSetRecoilState(accessTokenState);
  const setRefreshToken = useSetRecoilState(refreshTokenState);
  const setUserInfo = useSetRecoilState(userInfoState);

  return () => {
    setAccessToken('');
    setRefreshToken('');
    setUserInfo('');
    clearLoginInfo();
  };
};
