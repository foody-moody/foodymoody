import { useParams } from 'react-router-dom';
import { useGetFollows } from 'service/queries/follow';
import { styled } from 'styled-components';
import { customScrollStyle } from 'styles/customStyle';
import { useAuthState } from 'hooks/auth/useAuth';
import { useIntersectionObserver } from 'hooks/useObserver';
import { FollowListRow } from './FollowListRow';

type Props = {
  followType: 'followers' | 'followings';
  rootRef: React.MutableRefObject<HTMLDivElement | null>;
};

export const FollowList: React.FC<Props> = ({ followType, rootRef }) => {
  const { id } = useParams();
  const { userInfo } = useAuthState();
  const USER_ID = id || userInfo.id;
  const {
    followList,
    hasNextPage,
    fetchNextPage,
    // error,
  } = useGetFollows(followType, USER_ID);
  console.log(followList, 'followList');

  const { observeTarget } = useIntersectionObserver({
    callbackFn: () => {
      hasNextPage && fetchNextPage();
    },
    rootRef,
  });

  return (
    <Wrapper>
      {followList.length === 0 ? (
        <Info>목록이 없습니다</Info>
      ) : (
        followList?.map((followListItem: FollowListItem, index) => {
          const isLastItem = index === followList.length - 1;

          return (
            <FollowListRow
              followListItem={followListItem}
              key={followListItem.id}
              ref={isLastItem ? observeTarget : null}
              isAuthor={followListItem.id === userInfo.id}
            />
          );
        })
      )}
    </Wrapper>
  );
};

const Wrapper = styled.ul`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
  height: 300px;
  max-height: 300px;
  overflow: auto;
  overscroll-behavior: contain;
  ${customScrollStyle}
`;

const Info = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font: ${({ theme: { fonts } }) => fonts.displayB16};
  color: ${({ theme: { colors } }) => colors.textTertiary};
`;
