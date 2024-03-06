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
  const [notiCount, setNotiCount] = useState(0);
  const { isLogin } = useAuthState();
  const accessToken = useRecoilValue(accessTokenState);

  useEffect(() => {
    if (isLogin) {
      const updateNotiCount = (newCount: number) => {
        setNotiCount((currentCount) => {
          if (currentCount !== newCount) {
            return newCount;
          }
          return currentCount;
        });
      };

      const eventSource = new EventSourcePolyfill(`${BASE_API_URL}/sse`, {
        headers: {
          'Content-Type': 'text/event-stream',
          Authorization: `Bearer ${accessToken}`,
        },
      });

      eventSource.addEventListener('notification', (event) => {
        const messageEvent = event as MessageEvent;
        const { count } = JSON.parse(messageEvent.data);
        updateNotiCount(count);
      });

      eventSource.onerror = (err) => {
        if (eventSource.readyState === EventSource.CLOSED) {
          console.log(err, 'SSE closed');
        }
        return; // 그 전의 에러가 너무 시끄러워서 넣었는데 다른 방법 있으면 추천 plz
      };

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
