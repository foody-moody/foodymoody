import React, { useState } from 'react';
import { styled } from 'styled-components';
import { formatTimeStamp } from 'utils/formatTimeStamp';
import { FlexColumnBox } from '../feedUserInfo/FeedUserInfo';
import { Input } from '../input/Input';
import { UserImage } from '../userImage/UserImage';

type Props = {
  imageUrl: string;
  nickname: string;
  createdAt: string;
  comment: string;
};

export const CommentItem: React.FC<Props> = ({
  imageUrl,
  nickname,
  createdAt,
  comment,
}) => {
  const [isEdit, setIsEdit] = useState(false);

  const isAuthor = true;

  const handleEdit = () => {
    setIsEdit(true);
  };

  const handleSubmit = () => {
    console.log('submit comment');
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
            <p>{nickname}</p>
            <span>{formattedTimeStamp}</span>
          </ContentHeader>
          {isEdit ? <Input variant="ghost" /> : <p>{comment}</p>}
        </FlexColumnBox>
      </ContentLeft>
      {isAuthor && (
        <ContentRight>
          {isEdit ? (
            <p onClick={handleSubmit}>완료</p>
          ) : (
            <p onClick={handleEdit}>수정</p>
          )}
          <Delete onClick={handleDelete}>삭제</Delete>
        </ContentRight>
      )}
    </Wrapper>
  );
};

const Wrapper = styled.li`
  display: flex;
  justify-content: space-between;
  width: 100%;
  font: ${({ theme: { fonts } }) => fonts.displayM14};
`;

const ContentLeft = styled.div`
  display: flex;
  gap: 20px;
  align-items: center;
`;

const ContentHeader = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
  p {
    font: ${({ theme: { fonts } }) => fonts.displayB14};
  }
  span {
    color: ${({ theme: { colors } }) => colors.textSecondary};
  }
`;

const ContentRight = styled.div`
  display: flex;
  gap: 12px;
  cursor: pointer;

  height: fit-content;
  font: ${({ theme: { fonts } }) => fonts.displayM12};
`;

const Delete = styled.span`
  color: ${({ theme: { colors } }) => colors.red};
`;
