import React, { useEffect } from 'react';
import { styled } from 'styled-components';
import { useInput } from 'hooks/useInput';
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
              onChange={handleChange}
            />
          </Content>
          <Content>
            <label htmlFor="menu">메뉴 별점</label>
            <StarRating index={rate} onClick={handleStarClick} />
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
  border: 1px solid ${({ theme: { colors } }) => colors.black};
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
  border: 1px solid ${({ theme: { colors } }) => colors.black};
`;

// const ImageWrapper = styled.div`
//   min-width: 95px;
//   max-width: 95px;
//   min-height: 95px;
//   max-height: 95px;
//   background-color: yellow;
//   position: relative;

//   display: flex;
//   justify-content: center;
//   align-items: center;

//   &::after {
//     content: '';
//     position: absolute;
//     top: 0;
//     left: 0;
//     width: 95px;
//     height: 95px;
//     background-color: rgba(0, 0, 0, 0.3);
//     /* pointer-events: none; // Make sure the overlay doesn't block interaction */
//     cursor: pointer;
//   }

//   img {
//     width: 100%;
//     height: 100%;
//     object-fit: cover;
//   }

//   svg {
//     position: absolute;
//     top: 50%;
//     left: 50%;
//     transform: translate(-50%, -50%);
//     z-index: 10;
//   }
// `;

const ContentBody = styled.div`
  width: 100%;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  display: flex;
  flex-direction: column;
  gap: 8px;
`;

const Content = styled.div`
  width: 100%;

  border: 1px solid ${({ theme: { colors } }) => colors.black};

  display: flex;
  flex-direction: column;

  label {
    font: ${({ theme: { fonts } }) => fonts.displayM10};
    color: ${({ theme: { colors } }) => colors.textPrimary};
    border: 1px solid ${({ theme: { colors } }) => colors.black};
  }
`;
