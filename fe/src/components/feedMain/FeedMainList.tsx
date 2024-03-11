import { useAllFeeds } from 'service/queries/feed';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { MainFeedItem } from 'components/feedMain/FeedMainItem';
import { useIntersectionObserver } from 'hooks/useObserver';

/* TODO. 에러처리추가하기 */
export const FeedMainList = () => {
  const {
    feeds,
    hasNextPage,
    fetchNextPage,
    // error,
  } = useAllFeeds();

  const { observeTarget } = useIntersectionObserver({
    callbackFn: () => {
      hasNextPage && fetchNextPage();
    },
  });

  return (
    <Wrapper>
      {feeds?.map((feed: MainFeed, index: number) => {
        const isLastItem = index === feeds?.length - 2;

        return (
          <MainFeedItem
            feed={feed}
            key={feed?.id}
            ref={isLastItem ? observeTarget : null}
          />
        );
      })}
      {!hasNextPage && <TextBox>모든 피드를 확인했어요!</TextBox>}
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

  ${media.xs} {
    padding-bottom: 59px;
  }
`;

const TextBox = styled.p`
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  padding: 16px;
  width: 100%;
  max-width: 564px;
  text-align: center;
  background-color: ${({ theme: { colors } }) => colors.bgPrimary100};
  font: ${({ theme: { fonts } }) => fonts.displayB14};
`;
