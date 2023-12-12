import React, { useState } from 'react';
import { useDeleteComment, usePutComment } from 'service/queries/comment';
import { styled } from 'styled-components';
import { useAuthState } from 'hooks/auth/useAuth';
import { useInput } from 'hooks/useInput';
import { formatTimeStamp } from 'utils/formatTimeStamp';
// import { TextButton } from '../button/TextButton';
import { DotGhostIcon } from '../icon/icons';
import { Input } from '../input/Input';
import { useModal } from '../modal/useModal';
import { UserImage } from '../userImage/UserImage';

type Props = {
  createdAt: string;
  comment: CommentItem;
};

export const CommentItem: React.FC<Props> = ({ createdAt, comment }) => {
  const { openModal, closeModal } = useModal<'commentAlert'>();
  const { mutate: editMutate } = usePutComment();
  const { mutate: deleteMutate } = useDeleteComment();
  const { value, handleChange, isValid } = useInput({
    validator: (value) =>
      value.trim().length !== 0 && value.trim().length < 200,
  });
  const [isEdit, setIsEdit] = useState(false);
  const { isLogin, userInfo } = useAuthState();
  const isAuthor = userInfo?.id === comment.member.id;

  const handleEdit = () => {
    setIsEdit(true);
  };

  const handleEditSubmit = (commentId: string) => {
    isValid &&
      editMutate({
        id: commentId,
        body: { content: value },
      });

    setIsEdit(false);
  };

  const handleDelete = () => {
    console.log(comment.id, ' now comment ID');

    deleteMutate(comment.id);
  };

  const handleAlert = () => {
    const modalProps = {
      onClose: () => {
        closeModal('commentAlert');
      },
      onReport: () => {
        console.log('신고하기');
        closeModal('commentAlert');
      },
      ...(isAuthor && {
        onDelete: () => {
          handleDelete();
          closeModal('commentAlert');
        },
        onEdit: () => {
          handleEdit();
          closeModal('commentAlert');
        },
      }),
    };

    openModal('commentAlert', modalProps);
  };

  const formattedTimeStamp = formatTimeStamp(createdAt);

  return (
    <Wrapper>
      <ContentLeft>
        <UserImage imageUrl={comment.member.imageUrl} />
        <FlexColumnBox>
          <ContentHeader>
            <Nickname>{comment.member.nickname}</Nickname>
            <TimeStamp>{formattedTimeStamp}</TimeStamp>
          </ContentHeader>
          {isEdit ? (
            <Input
              variant="ghost"
              limitedLength={200}
              value={value}
              onChangeValue={handleChange}
              onPressEnter={() => handleEditSubmit(comment.id)}
            />
          ) : (
            comment.content
          )}
        </FlexColumnBox>
      </ContentLeft>
      {isLogin && (
        <ContentRight>
          <DotGhostIcon onClick={handleAlert} />
        </ContentRight>
      )}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
  font: ${({ theme: { fonts } }) => fonts.displayM14};
`;

const ContentLeft = styled.div`
  width: 100%;
  display: flex;
  gap: 20px;
  align-items: flex-start;
`;

const FlexColumnBox = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 4px;
`;

const ContentHeader = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
`;

const Nickname = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayB14};
`;

const ContentRight = styled.div`
  display: flex;
  gap: 12px;
  cursor: pointer;
  width: fit-content;
  height: fit-content;
  white-space: nowrap;
  font: ${({ theme: { fonts } }) => fonts.displayM12};
`;

const TimeStamp = styled.span`
  white-space: nowrap;
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;
