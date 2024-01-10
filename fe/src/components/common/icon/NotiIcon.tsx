import { EventSourcePolyfill } from 'event-source-polyfill';
import { jwtDecode } from 'jwt-decode';
import { useEffect, useState } from 'react';
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
  const [authToken, setAuthToken] = useState<string | null>(null);

  const { isLogin } = useAuthState();
  useEffect(() => {
    if (isLogin) {
      const currentToken = getAccessToken();

      const eventSource = new EventSourcePolyfill(`${BASE_API_URL}/sse`, {
        headers: {
          'Content-Type': 'text/event-stream',
          Authorization: `Bearer ${currentToken}`,
        },
      });

      eventSource.addEventListener('notification', (event) => {
        const messageEvent = event as MessageEvent;
        const { count } = JSON.parse(messageEvent.data);

        if (notiCount !== count) {
          setNotiCount(count);
        }
      });

      eventSource.onerror = async () => {
        if (eventSource.readyState === EventSource.CLOSED) {
          const token = getRefreshToken();
          if (!token) {
            clearLoginInfo();
            return;
          }
          try {
            const { accessToken, refreshToken } = await fetchRefresh(token);
            const payload = jwtDecode(accessToken);
            // TODO 백에서 refresh에 변동이 있을수있음
            setAccessToken(accessToken);
            setRefreshToken(refreshToken);
            setUserInfo(JSON.stringify(payload));
            if (accessToken !== authToken) {
              setAuthToken(accessToken);
            }
            return;
          } catch (error) {
            clearLoginInfo();
            return;
          }
        }
      };

      return () => {
        eventSource.close();
      };
    }
  }, [isLogin, authToken]);

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
