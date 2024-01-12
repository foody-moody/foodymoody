import {
  useAllNotifications,
  useReadNotification,
} from 'service/queries/notification';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { useIntersectionObserver } from 'hooks/useObserver';
import { NotiItem } from './NotiItem';

export const NotiList = () => {
  const {
    notifications,
    hasNextPage,
    fetchNextPage,
    // error,
  } = useAllNotifications();

  const { mutate: readNotiMutate } = useReadNotification();

  const { observeTarget } = useIntersectionObserver({
    callbackFn: () => {
      hasNextPage && fetchNextPage();
    },
  });

  const handleNotificationClick = (notificationId: string) => {
    readNotiMutate(notificationId);
  };

  return (
    <Wrapper>
      {notifications?.map((notification: NotificationItem, index) => {
        const isLastItem = index === notifications.length - 1;

        return (
          <NotiItem
            notification={notification}
            // key={notification.notificationId}
            ref={isLastItem ? observeTarget : null}
            onClick={handleNotificationClick}
          />
        );
      })}
    </Wrapper>
  );
};

const Wrapper = styled.ul`
  display: flex;
  width: 100%;
  position: relative;
  flex-direction: column;
  gap: 16px;
  padding: 16px 0;
  align-items: center;

  /* ${media.xs} {
    padding-bottom: 59px;
  } */
`;
