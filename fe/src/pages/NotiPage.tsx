import { Suspense } from 'react';
import { useLocation } from 'react-router-dom';
import {
  useDeleteReadNotification,
  useReadAllNotification,
} from 'service/queries/notification';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { Button } from 'components/common/button/Button';
import { DeferredComponent } from 'components/common/skeleton/DeferredComponent';
import { NotiSkeleton } from 'components/common/skeleton/NotiSkeleton';
import { NotiList } from 'components/notification/NotiList';
import { DetailFeedModalPage } from './DetailFeedPage';

export const NotiPage = () => {
  const location = useLocation();
  const background = location.state && location.state.background;

  const { mutate: readAllNotifications } = useReadAllNotification();
  const { mutate: deleteReadNotifications } = useDeleteReadNotification();

  const handleReadAllClick = () => {
    readAllNotifications();
  };

  const handleDeleteReadClick = () => {
    deleteReadNotifications();
  };

  return (
    <Wrapper>
      <Buttons>
        <Button
          size="s"
          backgroundColor="white"
          shadow
          onClick={handleReadAllClick}
        >
          모든 알림 읽기
        </Button>
        <Button
          size="s"
          backgroundColor="white"
          shadow
          onClick={handleDeleteReadClick}
        >
          읽은 알림 삭제
        </Button>
      </Buttons>
      <Suspense
        fallback={
          <DeferredComponent>
            <NotiSkeleton notiCount={10} />
          </DeferredComponent>
        }
      >
        <NotiList />
      </Suspense>

      {background === 'notiDetailFeed' && <DetailFeedModalPage />}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  max-width: 540px;
  margin: 20px auto;

  ${media.md} {
    margin: 16px auto;
  }
`;

const Buttons = styled.div`
  display: flex;
  flex: 1 1;
  gap: 20px;
  padding: 16px;
`;
