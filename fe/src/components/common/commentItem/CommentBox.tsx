import { useState, forwardRef } from 'react';
import { useGetReplies, usePostReply } from 'service/queries/reply';
import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import { useAuthState } from 'hooks/auth/useAuth';
import { useInput } from 'hooks/useInput';
import { ArrowDownIcon, ArrowUpIcon } from '../icon/icons';
import { CommentInput } from '../input/CommentInput';
import { CommentItem } from './CommentItem';

type Props = {
  createdAt: string;
  comment: CommentItem;
};

export const CommentBox = forwardRef<HTMLLIElement, Props>(
  ({ createdAt, comment }, ref) => {
    const {
      replies,
      refetch: getReplies,
      fetchNextPage: fetchNextReplies,
      hasNextPage,
    } = useGetReplies(comment.id);
    const { mutate: replyMutate } = usePostReply(comment.id);
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

    const handleToggleReplies = () => {
      getReplies();
      setShowReplies(!showReplies);
    };

    return (
      <Wrapper ref={ref}>
        <CommentItem createdAt={createdAt} comment={comment} />
        <ReplyButtonBox>
          <TextButton color="orange" size="s" onClick={handleToggleReplyInput}>
            답글 달기
          </TextButton>
          {comment.hasReply && (
            <TextButton color="black" size="s" onClick={handleToggleReplies}>
              {showReplies ? <ArrowUpIcon /> : <ArrowDownIcon />}
              답글 {comment.replyCount}개
            </TextButton>
          )}
        </ReplyButtonBox>
        {isReplying && (
          <ReplyInputBox>
            <CommentInput
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
              <CommentItem
                key={reply.id}
                createdAt={
                  reply.createdAt === reply.updatedAt
                    ? reply.createdAt
                    : reply.updatedAt
                }
                comment={reply}
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
  gap: 12px;
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
