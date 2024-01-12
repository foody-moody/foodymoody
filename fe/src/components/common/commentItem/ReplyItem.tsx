import React, { useRef, useState } from 'react';
import { useDeleteComment, usePutComment } from 'service/queries/comment';
import { useDeleteReplyLike, usePostReplyLike } from 'service/queries/like';
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

type Props = {
  commentId: string;
  reply: ReplyItemType;
  createdAt: string;
};

export const ReplyItem: React.FC<Props> = ({ commentId, reply, createdAt }) => {
  console.log(reply, ' now ReplyItems');
  const [isEdit, setIsEdit] = useState(false);
  const { openModal, closeModal } = useModal<'commentAlert'>();
  const { isLogin, userInfo } = useAuthState();
  const { navigateToLogin } = usePageNavigator();
  const { mutate: editMutate } = usePutComment();
  const { mutate: deleteMutate } = useDeleteComment();
  const { mutate: likeMutate } = usePostReplyLike();
  const { mutate: unLikeMutate } = useDeleteReplyLike();

  const { value, handleChange, isValid } = useInput({
    initialValue: reply.content,
    validator: (value) =>
      value.trim().length !== 0 && value.trim().length < 200,
  });

  const inputRef = useRef<HTMLInputElement>(null);
  const isAuthor = userInfo?.id === reply.member.id;
  const LikeIcon = reply.liked ? HeartSmallFill : HeartSmallEmpty;
  const formattedTimeStamp = formatTimeStamp(createdAt);

  const handleEdit = () => {
    setIsEdit(true);
    console.log(inputRef, ' now inputRef');
    //이거 왜안됨
    inputRef?.current?.focus();
  };

  const handleEditSubmit = (replyId: string) => {
    isValid &&
      editMutate({
        id: replyId,
        body: { content: value },
      });

    setIsEdit(false);
  };

  const handleDelete = () => {
    console.log(reply.id, ' now reply ID');

    deleteMutate(reply.id);
  };

  const handleAlert = () => {
    const modalProps = {
      onClose: () => {
        setIsEdit(false);
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
    console.log(reply.id, ' now reply ID', commentId, 'commentId');

    if (isLogin) {
      reply.liked
        ? unLikeMutate({ commentId: commentId, replyId: reply.id })
        : likeMutate({ commentId: commentId, replyId: reply.id });
    } else {
      navigateToLogin();
    }
  };

  return (
    <Wrapper>
      <ReplyRow>
        <ContentLeft>
          <UserImage
            imageUrl={
              reply.member.imageUrl || generateDefaultUserImage(reply.member.id)
            }
          />
          <FlexColumnBox>
            <ContentHeader>
              <Nickname>{reply.member.nickname}</Nickname>
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
                    onPressEnter={() => handleEditSubmit(reply.id)}
                    onBlur={() => {
                      setIsEdit(false);
                    }}
                  />
                </Input.CenterContent>
              </Input>
            ) : (
              reply.content
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
        <p>좋아요 {reply.likeCount}개</p>
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
