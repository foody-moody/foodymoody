import { useAllProfileFeeds } from 'service/queries/feed';
import { styled } from 'styled-components';
import { EmptyProfileFeeds } from 'components/common/help/EmptyProfileFeeds';
import { useAuthState } from 'hooks/auth/useAuth';
import { useIntersectionObserver } from 'hooks/useObserver';
import { FeedProfileItem } from './FeedProfleItem';

/* TODO. 에러처리추가하기 */
export const FeedProfileList = () => {
  const { userInfo } = useAuthState();
  const { profileFeeds, hasNextPage, fetchNextPage } = useAllProfileFeeds(
    userInfo.id
  );

  const { observeTarget } = useIntersectionObserver({
    callbackFn: () => {
      hasNextPage && fetchNextPage();
    },
  });

  return (
    <>
      {profileFeeds.length === 0 ? (
        <EmptyProfileFeeds />
      ) : (
        <FeedsWrapper>
          {profileFeeds?.map((feed: ProfileFeed, index: number) => {
            const isLastItem = index === profileFeeds.length - 2;

            return (
              <FeedProfileItem
                key={feed.id}
                feed={feed}
                ref={isLastItem ? observeTarget : null}
              />
            );
          })}
        </FeedsWrapper>
      )}
    </>
  );
};

const FeedsWrapper = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2px;
`;
