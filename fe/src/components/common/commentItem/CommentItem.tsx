import React, { useState } from 'react';
import { styled } from 'styled-components';
import { usePutComment } from 'queries/comment';
import { useAuthState } from 'hooks/auth/useAuth';
import { useInput } from 'hooks/useInput';
import { formatTimeStamp } from 'utils/formatTimeStamp';
// import { TextButton } from '../button/TextButton';
import { DotGhostIcon } from '../icon/icons';
import { Input } from '../input/Input';
import { UserImage } from '../userImage/UserImage';

type Props = {
  imageUrl: string;
  nickname: string;
  createdAt: string;
  content: string;
};

export const CommentItem: React.FC<Props> = ({
  imageUrl,
  nickname,
  createdAt,
  content,
}) => {
  const COMMENT_ID = '1';
  const { mutate: editMutate } = usePutComment();
  const { value, handleChange, isValid } = useInput({
    validator: (value) =>
      value.trim().length !== 0 && value.trim().length < 200,
  });
  const [isEdit, setIsEdit] = useState(false);
  // TODO 로그인된 유저의 id와 comment의 userId가 같으면 수정, 삭제 가능
  // TODO alert에 isAuthor 등
  const { isLogin } = useAuthState();
  // const isAuthor = userInfo.id === ;
  const isAuthor = true;

  const handleEdit = () => {
    setIsEdit(true);
  };

  const handleSubmit = (commentId: string) => {
    console.log('submit comment', isValid);
    editMutate({
      id: commentId,
      body: { content: value },
    });
    setIsEdit(false);
  };

  const handleDelete = () => {
    console.log('delete comment');
  };

  const formattedTimeStamp = formatTimeStamp(createdAt);

  return (
    <Wrapper>
      <ContentLeft>
        <UserImage imageUrl={imageUrl} />
        <FlexColumnBox>
          <ContentHeader>
            <Nickname>{nickname}</Nickname>
            <TimeStamp>{formattedTimeStamp}</TimeStamp>
          </ContentHeader>
          {isEdit ? (
            <Input
              variant="ghost"
              limitedLength={200}
              value={value}
              onChangeValue={handleChange}
            />
          ) : (
            content
          )}
          {/* <ContentBody>
            <TextButton color="orange" size="s" onClick={() => {}}>
              답글 달기
            </TextButton>
          </ContentBody> */}
        </FlexColumnBox>
      </ContentLeft>
      {isLogin && (
        <ContentRight>
          <DotGhostIcon
            onClick={() => {
              console.log(
                isAuthor,
                handleEdit,
                handleDelete,
                handleSubmit(COMMENT_ID)
              );
            }}
          />
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

// const ContentBody = styled.div`
//   display: flex;
//   gap: 8px;
// `;

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

// const Delete = styled.span`
//   color: ${({ theme: { colors } }) => colors.red};
// `;

const TimeStamp = styled.span`
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;
