import { useGetComments } from 'service/queries/comment';
import { styled } from 'styled-components';
import { CommentBox } from 'components/common/commentItem/CommentBox';
import { useIntersectionObserver } from 'hooks/useObserver';

type Props = {
  feedId: string;
  rootRef: React.MutableRefObject<HTMLDivElement | null>;
};

export const CommentList = ({ feedId, rootRef }: Props) => {
  const { comments, hasNextPage, fetchNextPage } = useGetComments(feedId);
  const { observeTarget } = useIntersectionObserver({
    callbackFn: () => {
      hasNextPage && fetchNextPage();
    },
    rootRef,
  });

  return (
    <Wrapper>
      {comments?.map((comment, index) => {
        const isLastItem = index === comments.length - 2;

        return (
          <CommentBox
            key={comment.id}
            ref={isLastItem ? observeTarget : null}
            comment={comment}
            createdAt={
              comment.createdAt === comment.updatedAt
                ? comment.createdAt
                : comment.updatedAt
            }
          />
        );
      })}
    </Wrapper>
  );
};

const Wrapper = styled.ul`
  display: flex;
  flex-direction: column;
  gap: 24px;
  padding-bottom: 10px;
`;
