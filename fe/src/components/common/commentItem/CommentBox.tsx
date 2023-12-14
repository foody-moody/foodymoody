import { useState, forwardRef } from 'react';
import { useGetReplies, usePostReply } from 'service/queries/reply';
import { styled } from 'styled-components';
import { CommentInputContainer } from 'components/commentInput/CommentInputContainer';
import { TextButton } from 'components/common/button/TextButton';
import { useAuthState } from 'hooks/auth/useAuth';
import { useInput } from 'hooks/useInput';
import { ArrowDownIcon, ArrowUpIcon } from '../icon/icons';
// import { CommentInput } from '../input/CommentInput';
import { Spinner } from '../loading/spinner';
import { CommentItem } from './CommentItem';
import { ReplyItem } from './ReplyItem';

type Props = {
  createdAt: string;
  comment: CommentItemType;
};
//TODO heartCount isHearted
export const CommentBox = forwardRef<HTMLLIElement, Props>(
  ({ createdAt, comment }, ref) => {
    const {
      replies,
      refetch: getReplies,
      isFetching,
      fetchNextPage: fetchNextReplies,
      hasNextPage,
    } = useGetReplies(comment.id);
    const { mutate: replyMutate } = usePostReply(comment.id, () => {
      submitCallback();
    });
    const { value, handleChange, isValid } = useInput({
      validator: (value) =>
        value.trim().length !== 0 && value.trim().length < 200,
    });
    const { isLogin } = useAuthState();
    const [isReplying, setIsReplying] = useState(false);
    const [showReplies, setShowReplies] = useState(false);

    const handleToggleReplyInput = () => {
      isLogin && setIsReplying(!isReplying);
    };

    const handleSubmitReply = () => {
      isValid &&
        replyMutate({
          content: value,
        });

      handleChange('');
      setIsReplying(false);
    };

    const submitCallback = () => {
      getReplies();
      setShowReplies(true);
    };

    const handleToggleReplies = () => {
      (!replies || replies.length === 0) && getReplies();
      setShowReplies(!showReplies);
    };

    return (
      <Wrapper ref={ref}>
        <CommentItem createdAt={createdAt} comment={comment} />
        <ReplyButtonBox>
          <p>좋아요 {comment.likeCount}개</p>
          {/* 좋아요 누른 사람의 목록을 보여줄 것인지? */}
          <TextButton color="black" size="s" onClick={handleToggleReplyInput}>
            답글 달기
          </TextButton>
          {comment.hasReply && (
            <TextButton color="black" size="s" onClick={handleToggleReplies}>
              {showReplies ? <ArrowUpIcon /> : <ArrowDownIcon />}
              답글 {comment.replyCount}개
            </TextButton>
          )}
          <Spinner isLoading={isFetching} />
        </ReplyButtonBox>
        {isReplying && (
          <ReplyInputBox>
            <CommentInputContainer
              value={value}
              limitedLength={200}
              onChangeValue={handleChange}
              onSubmitComment={handleSubmitReply}
            />
          </ReplyInputBox>
        )}

        {showReplies && (
          <ReplyContainer>
            {replies.map((reply) => (
              <ReplyItem
                key={reply.id}
                commentId={comment.id}
                createdAt={
                  reply.createdAt === reply.updatedAt
                    ? reply.createdAt
                    : reply.updatedAt
                }
                reply={reply}
              />
            ))}
            {hasNextPage && (
              <MoreButton>
                <TextButton
                  color="black"
                  size="s"
                  onClick={() => {
                    fetchNextReplies();
                  }}
                >
                  답글 더보기
                </TextButton>
              </MoreButton>
            )}
          </ReplyContainer>
        )}
      </Wrapper>
    );
  }
);

const Wrapper = styled.li`
  display: flex;
  flex-direction: column;
  gap: 8px;
  width: 100%;
`;

const ReplyButtonBox = styled.div`
  padding: 0 0 0 60px;
  display: flex;
  align-items: center;
  gap: 12px;

  p {
    font: ${({ theme: { fonts } }) => fonts.displayM12};
  }
`;

const MoreButton = styled.div`
  padding: 0 0 0 60px;
`;

const ReplyInputBox = styled.div`
  padding: 0 0 16px 60px;
`;

const ReplyContainer = styled.ul`
  display: flex;
  flex-direction: column;

  gap: 16px;
  padding: 0 0 0 60px;
`;
