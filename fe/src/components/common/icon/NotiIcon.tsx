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
  // const [eventSource, setEventSource] = useState<EventSourcePolyfill | null>();
  const { isLogin, userInfo } = useAuthState();

  useEffect(() => {
    console.log('is changed userinfo');

    if (isLogin) {
      const token = getAccessToken();
      const reg = getRefreshToken();
      console.log(token, reg, 'sse start tokens');

      const eventSource = new EventSourcePolyfill(`${BASE_API_URL}/sse`, {
        headers: {
          'Content-Type': 'text/event-stream',
          Authorization: `Bearer ${token}`,
        },
      });
      console.log('eventSource', eventSource);

      eventSource.addEventListener('notification', (event) => {
        const messageEvent = event as MessageEvent;
        const { count } = JSON.parse(messageEvent.data);

        if (notiCount !== count) {
          setNotiCount(count);
        }
      });

      eventSource.onerror = async (error) => {
        if (!eventSource) return;
        console.log(eventSource.readyState, 'ventSource.readyState');
        console.log(EventSource.CLOSED, 'ventSource.CLOSED');
        // oldcheck?
        if (eventSource.readyState === EventSource.CLOSED) {
          // window.setTimeout(() => {
          //   console.log('reconnect 할거임');

          //   return eventSource;
          // }, 0);
          // return;
          console.log('sse에러발생', error);

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
            console.log('sse리프레시 토큰 다시받아서 요청 보낼거임');
            console.log(
              accessToken,
              refreshToken,
              'SSE accessToken,refreshToken'
            );
          } catch (error) {
            console.log(error, 'sse리프레시 토큰 만료~~ 에러 ');
            clearLoginInfo();
            //error던지고 에러바운더리로 why? 그냥 이동하면 에러페이지가 뜸
            return;
          }
        }
      };

      return () => {
        eventSource && eventSource.close();
      };
    }
  }, [isLogin, notiCount, userInfo]);

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
