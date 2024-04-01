import { EventSourcePolyfill } from 'event-source-polyfill';
import { useEffect, useState } from 'react';
import { useRecoilValue } from 'recoil';
import { accessTokenState } from 'recoil/localStorage/atom';
import { BASE_API_URL } from 'service/axios/fetcher';
import { styled } from 'styled-components';
import { useAuthState } from 'hooks/auth/useAuth';
import { NotificationIcon } from './icons';

type Props = {
  onClick?(): void;
};

export const NotiIcon: React.FC<Props> = ({ onClick }) => {
  const [notiCount, setNotiCount] = useState<number>(0);
  const { isLogin } = useAuthState();
  const accessToken = useRecoilValue<string>(accessTokenState);

  useEffect(() => {
    let eventSource: EventSourcePolyfill | null = null;
    const MAX_RECONNECT_ATTEMPTS = 3;
    let reconnectAttempts = 0;

    if (!isLogin || !accessToken) {
      return;
    }

    const openConnection = () => {
      if (reconnectAttempts >= MAX_RECONNECT_ATTEMPTS) {
        console.log('Max reconnect attempts reached, will not reconnect.');
        return;
      }

      eventSource = new EventSourcePolyfill(`${BASE_API_URL}/sse`, {
        headers: {
          'Content-Type': 'text/event-stream',
          Authorization: `Bearer ${accessToken}`,
        },
      });

      eventSource.addEventListener('notification', (event) => {
        const messageEvent = event as MessageEvent;
        const { count } = JSON.parse(messageEvent.data);
        setNotiCount(count);
      });

      eventSource.onerror = (err) => {
        console.error('SSE error:', err);
        if (eventSource?.readyState === EventSource.CLOSED) {
          console.log('SSE closed, attempting to reconnect...');
          eventSource.close();
          reconnectAttempts += 1;
          setTimeout(openConnection, 5000); // 5초 후 재연결 시도
        }
      };
    };

    openConnection();

    return () => {
      eventSource?.close();
    };
  }, [isLogin, accessToken]);

  return (
    <Wrapper onClick={onClick}>
      <NotificationIcon />
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
