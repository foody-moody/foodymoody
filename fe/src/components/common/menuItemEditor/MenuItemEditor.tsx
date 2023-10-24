import React, { useEffect } from 'react';
import { styled } from 'styled-components';
import { useInput } from 'hooks/useInput';
import { useMenuItem } from 'hooks/useMenuItem';
import { useStarRating } from 'hooks/useStarRating';
import { CloseSmallIcon } from '../icon/icons';
import { Input } from '../input/Input';
import { StarRating } from '../starRating/StarRating';

type Props = {
  index: number;
  menu: FeedMenuType;
  imageBox: React.ReactNode;
  onEditMenuName: (index: number, name: string) => void;
  onEditStarRating: (index: number, rate: number) => void;
  onRemove: (index: number) => void;
};

export const MenuItemEditor: React.FC<Props> = (
  { index, menu, imageBox, onEditMenuName, onEditStarRating, onRemove }
) => {
  const { value, handleChange } = useInput({
    initialValue: menu.name,
  });
  const { rate, handleStarClick } = useStarRating(menu.numStar);

  return (
    <Wrapper>
      <LeftContent>
        {imageBox}
        <ContentBody>
          <Content>
            <label htmlFor="menu">메뉴 이름</label>
            <Input
              id="menu"
              variant="ghost"
              value={value}
              onChange={(value) => {
                handleChange(value);
              }}
              onBlur={() => {
                onEditMenuName(index, value);
              }}
            />
          </Content>
          <Content>
            <label>메뉴 별점</label>
            <StarRating
              index={rate}
              onClick={(newRate) => {
                const updatedRate = handleStarClick(newRate);
                onEditStarRating(index, updatedRate);
              }}
            />
          </Content>
        </ContentBody>
      </LeftContent>

      <CloseSmallIcon
        onClick={() => {
          onRemove(index);
        }}
      />
    </Wrapper>
  );
};

const Wrapper = styled.li`
  width: 100%;
  display: flex;
  box-sizing: border-box;
  justify-content: space-between;

  svg {
    cursor: pointer;
  }
`;

const LeftContent = styled.div`
  display: flex;
  box-sizing: border-box;
  width: 100%;
  gap: 16px;
`;

const ContentBody = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 8px;
`;

const Content = styled.div`
  width: 100%;

  display: flex;
  flex-direction: column;

  label {
    font: ${({ theme: { fonts } }) => fonts.displayM10};
    color: ${({ theme: { colors } }) => colors.textPrimary};
  }
`;
