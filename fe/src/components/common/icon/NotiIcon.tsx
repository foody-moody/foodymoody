import { EventSourcePolyfill } from 'event-source-polyfill';
import { jwtDecode } from 'jwt-decode';
import { useEffect, useState } from 'react';
import { useRecoilValue } from 'recoil';
import { accessTokenState } from 'recoil/localStorage/atom';
import { fetchRefresh } from 'service/axios/auth/login';
import { BASE_API_URL } from 'service/axios/fetcher';
import { styled } from 'styled-components';
import { useAuthState } from 'hooks/auth/useAuth';
import {
  clearLoginInfo,
  getAccessToken,
  getRefreshToken,
  setAccessToken,
  setRefreshToken,
  setUserInfo,
} from 'utils/localStorage';
import { NotificationIcon } from './icons';

type Props = {
  onClick?(): void;
};

export const NotiIcon: React.FC<Props> = ({ onClick }) => {
  const [notiCount, setNotiCount] = useState(0);

  const { isLogin } = useAuthState();
  // const currentToken = getAccessToken();
  // 여기 리코일로   const [authToken, setAuthToken] = useState<string | null>(null); 이거 바꿔서 디펜던시에 authToken넣어주고 되나 보기
  const accessToken = useRecoilValue(accessTokenState);
  console.log(accessToken, ' now accessToken');
  const token = localStorage.getItem('accessToken');
  console.log(token, ' now tokentokenaccessToken');

  useEffect(() => {
    if (isLogin) {
      const eventSource = new EventSourcePolyfill(`${BASE_API_URL}/sse`, {
        headers: {
          'Content-Type': 'text/event-stream',
          Authorization: `Bearer ${accessToken}`,
        },
      });

      eventSource.addEventListener('notification', (event) => {
        const messageEvent = event as MessageEvent;
        const { count } = JSON.parse(messageEvent.data);

        if (notiCount !== count) {
          setNotiCount(count);
        }
      });

      return () => {
        eventSource.close();
      };
    }
  }, [isLogin, accessToken]);

  return (
    <Wrapper>
      <NotificationIcon onClick={onClick} />
      {notiCount > 0 ? (
        <NotiCount>{notiCount > 99 ? '99+' : notiCount}</NotiCount>
      ) : null}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  position: relative;
  height: 32px;
  margin-top: 8px;
`;

const NotiCount = styled.div`
  position: absolute;
  top: -4px;
  right: -6px;
  font: ${({ theme: { fonts } }) => fonts.displayM10};
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  padding: 0px 4px;
  text-align: center;
  background-color: ${({ theme: { colors } }) => colors.orange};
  border-radius: ${({ theme: { radius } }) => radius.large};
`;
