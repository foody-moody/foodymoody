import { useParams } from 'react-router-dom';
import { useAllProfileFeeds } from 'service/queries/feed';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { EmptyProfileFeeds } from 'components/common/help/EmptyProfileFeeds';
import { useAuthState } from 'hooks/auth/useAuth';
import { useIntersectionObserver } from 'hooks/useObserver';
import { FeedProfileItem } from './FeedProfleItem';

/* TODO. 에러처리추가하기 */
export const FeedProfileList = () => {
  const { id } = useParams();
  const { userInfo } = useAuthState();
  const USER_ID = id || userInfo.id;

  const { profileFeeds, hasNextPage, fetchNextPage } =
    useAllProfileFeeds(USER_ID);

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
  ${media.xs} {
    grid-template-columns: repeat(2, 1fr);
  }
`;
