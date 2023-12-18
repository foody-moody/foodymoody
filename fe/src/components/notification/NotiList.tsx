import { useAllNotifications } from 'service/queries/notification';
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

  const { observeTarget } = useIntersectionObserver({
    callbackFn: () => {
      hasNextPage && fetchNextPage();
    },
  });

  return (
    <Wrapper>
      {notifications?.map((notification, index) => {
        const isLastItem = index === notifications.length - 1;

        return (
          <NotiItem
            notification={notification}
            key={notification.notificationId}
            ref={isLastItem ? observeTarget : null}
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
