import React, { useState } from 'react';
import { useDeleteComment, usePutComment } from 'service/queries/comment';
import { useDeleteCommentLike, usePostCommentLike } from 'service/queries/like';
import { styled } from 'styled-components';
import { useAuthState } from 'hooks/auth/useAuth';
import { useInput } from 'hooks/useInput';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { formatTimeStamp } from 'utils/formatTimeStamp';
import { DotGhostIcon, HeartSmallEmpty, HeartSmallFill } from '../icon/icons';
import { Input2 } from '../input/Input2';
import { InputField } from '../input/InputField';
import { useModal } from '../modal/Modal';
import { UserImage } from '../userImage/UserImage';

type Props = {
  createdAt: string;
  comment: ReplyItemType;
};

export const ReplyItem: React.FC<Props> = ({ createdAt, comment }) => {
  console.log(comment, ' now ReplyItems');
  const { navigateToLogin } = usePageNavigator();
  const { openModal, closeModal } = useModal<'commentAlert'>();
  const { mutate: editMutate } = usePutComment();
  const { mutate: deleteMutate } = useDeleteComment();
  const { value, handleChange, isValid } = useInput({
    validator: (value) =>
      value.trim().length !== 0 && value.trim().length < 200,
  });
  const [isEdit, setIsEdit] = useState(false);
  const { isLogin, userInfo } = useAuthState();

  const { mutate: likeMutate } = usePostCommentLike({
    isReply: true,
  });
  const { mutate: unLikeMutate } = useDeleteCommentLike({
    isReply: true,
  });

  const isAuthor = userInfo?.id === comment.member.id;
  const LikeIcon = comment.hearted ? HeartSmallFill : HeartSmallEmpty;
  const likeFn = comment.hearted ? unLikeMutate : likeMutate;
  const formattedTimeStamp = formatTimeStamp(createdAt);

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
  const handleSubmitLike = () => {
    if (isLogin) {
      likeFn(comment.id);
    } else {
      navigateToLogin();
    }
  };

  return (
    <Wrapper>
      <ReplyRow>
        <ContentLeft>
          <UserImage imageUrl={comment.member.imageUrl} />
          <FlexColumnBox>
            <ContentHeader>
              <Nickname>{comment.member.nickname}</Nickname>
              <TimeStamp>{formattedTimeStamp}</TimeStamp>
            </ContentHeader>
            {isEdit ? (
              <Input2 variant="ghost">
                <Input2.CenterContent>
                  <InputField
                    limitedLength={200}
                    value={value}
                    onChangeValue={handleChange}
                    onPressEnter={() => handleEditSubmit(comment.id)}
                  />
                </Input2.CenterContent>
              </Input2>
            ) : (
              comment.content
            )}
          </FlexColumnBox>
        </ContentLeft>
        {isLogin && (
          <ContentRight>
            <DotGhostIcon onClick={handleAlert} />
            <LikeIcon onClick={handleSubmitLike} />
          </ContentRight>
        )}
      </ReplyRow>
      <ReplyButtonBox>
        <p>좋아요 n개</p>
      </ReplyButtonBox>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  /* display: flex;
  justify-content: space-between;
  width: 100%;
  font: ${({ theme: { fonts } }) => fonts.displayM14}; */
  display: flex;
  flex-direction: column;
  gap: 8px;
  width: 100%;
`;

const ReplyRow = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
  font: ${({ theme: { fonts } }) => fonts.displayM14};
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
  flex-direction: column;
  align-items: flex-end;
  gap: 4px;
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
