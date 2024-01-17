import React, { useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDeleteComment, usePutComment } from 'service/queries/comment';
import { useDeleteCommentLike, usePostCommentLike } from 'service/queries/like';
import { styled } from 'styled-components';
import { useAuthState } from 'hooks/auth/useAuth';
import { useInput } from 'hooks/useInput';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { formatTimeStamp } from 'utils/formatTimeStamp';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';
import { DotGhostIcon, HeartSmallEmpty, HeartSmallFill } from '../icon/icons';
import { Input } from '../input/Input';
import { InputField } from '../input/InputField';
import { useModal } from '../modal/useModal';
import { UserImage } from '../userImage/UserImage';
import { PATH } from 'constants/path';

type Props = {
  createdAt: string;
  comment: CommentItemType;
};

export const CommentItem: React.FC<Props> = ({ createdAt, comment }) => {
  console.log(comment, ' now commentItems');
  const inputRef = useRef(null);
  const navigate = useNavigate();

  const [isEdit, setIsEdit] = useState(false);
  const { isLogin, userInfo } = useAuthState();
  const { navigateToLogin } = usePageNavigator();
  const { openModal, closeModal } = useModal<'commentAlert'>();
  const { mutate: editMutate } = usePutComment();
  const { mutate: deleteMutate } = useDeleteComment();
  const { mutate: likeMutate } = usePostCommentLike();
  const { mutate: unLikeMutate } = useDeleteCommentLike();

  const { value, handleChange, isValid } = useInput({
    initialValue: comment.content, //여기 추가함
    validator: (value) =>
      value.trim().length !== 0 && value.trim().length < 200,
  });

  const isAuthor = userInfo?.id === comment.member.id;
  const LikeIcon = comment.liked ? HeartSmallFill : HeartSmallEmpty;
  // const likeFn = comment.liked ? unLikeMutate : likeMutate;
  const formattedTimeStamp = formatTimeStamp(createdAt);

  const handleEdit = (inputRef?: React.RefObject<HTMLInputElement>) => {
    setIsEdit(true);
    console.log(inputRef, ' now inputRef');
    //이거 왜안됨
    inputRef && inputRef?.current?.focus();
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
          handleEdit(inputRef);
          closeModal('commentAlert');
        },
      }),
    };

    openModal('commentAlert', modalProps);
  };
  console.log(comment.id, ' now comment ID');
  const handleSubmitLike = () => {
    if (isLogin) {
      comment.liked ? unLikeMutate(comment.id) : likeMutate(comment.id);
    } else {
      navigateToLogin();
    }
  };

  const handleNavigateProfile = () => {
    navigate(PATH.PROFILE + '/' + comment.member.id);
    sessionStorage.setItem('profileId', comment.member.id);
  };

  return (
    <Wrapper>
      <ContentLeft>
        <UserImage
          imageUrl={
            comment.member.imageUrl ||
            generateDefaultUserImage(comment.member.id)
          }
          onClick={handleNavigateProfile}
        />
        <FlexColumnBox>
          <ContentHeader>
            <Nickname>{comment.member.nickname}</Nickname>
            <TimeStamp>{formattedTimeStamp}</TimeStamp>
          </ContentHeader>
          {isEdit ? (
            <Input variant="ghost">
              <Input.CenterContent>
                <InputField
                  ref={inputRef}
                  limitedLength={200}
                  value={value}
                  onChangeValue={handleChange}
                  onPressEnter={() => handleEditSubmit(comment.id)}
                  onBlur={() => {
                    setIsEdit(false);
                  }}
                />
              </Input.CenterContent>
            </Input>
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
