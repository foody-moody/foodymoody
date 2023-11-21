import { useState, forwardRef } from 'react';
import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import { useInput } from 'hooks/useInput';
import { ArrowDownIcon, ArrowUpIcon } from '../icon/icons';
import { CommentInput } from '../input/CommentInput';
import { CommentItem } from './CommentItem';

type Props = {
  imageUrl: string;
  nickname: string;
  createdAt: string;
  content: string;
  hasReply: boolean;
  replyCount: number;
};

export const CommentBox = forwardRef<HTMLLIElement, Props>(
  ({ imageUrl, nickname, createdAt, content, hasReply, replyCount }, ref) => {
    const { value, handleChange, isValid } = useInput({
      validator: (value) =>
        value.trim().length !== 0 && value.trim().length < 200,
    });
    const [isReplying, setIsReplying] = useState(false);
    const [showReplies, setShowReplies] = useState(false);

    const handleToggleReplyInput = () => {
      setIsReplying(!isReplying);
    };

    const handleSubmit = () => {
      console.log('is Comment Valid', {
        isCommentValid: isValid,
        commentValue: value,
      });

      // commentMutate({
      //   commentId: MOCK.id,
      //   content: value,
      // });

      setIsReplying(false);
    };

    const handleToggleReplies = () => {
      console.log('toggle replies');
      setShowReplies(!showReplies);
    };

    return (
      <Wrapper ref={ref}>
        <CommentItem
          imageUrl={imageUrl}
          nickname={nickname}
          createdAt={createdAt}
          content={content}
        />
        <ReplyButtonBox>
          <TextButton color="orange" size="s" onClick={handleToggleReplyInput}>
            답글 달기
          </TextButton>
          {hasReply && (
            <TextButton color="black" size="s" onClick={handleToggleReplies}>
              {showReplies ? <ArrowUpIcon /> : <ArrowDownIcon />}
              답글 {replyCount}개
            </TextButton>
          )}
        </ReplyButtonBox>
        {isReplying && (
          <ReplyInputBox>
            <CommentInput
              value={value}
              limitedLength={200}
              onChangeValue={handleChange}
              onSubmitComment={handleSubmit}
            />
          </ReplyInputBox>
        )}

        {showReplies && (
          <ReplyContainer>
            {Array.from({ length: 3 }).map((_, index) => (
              <CommentItem
                key={index}
                imageUrl="123"
                nickname="댓글닉넴"
                createdAt="2023-11-01T14:55:47.88735"
                content="댓글임니당 ㅎㅎ댓글임니당댓글임니당댓글임니당댓글임니당댓글임니당댓글임니당"
              />
            ))}
            {/*  TODO hasnextpage 조건 추가*/}
            <MoreButton>
              <TextButton color="black" size="s" onClick={() => {}}>
                답글 더보기
              </TextButton>
            </MoreButton>
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
