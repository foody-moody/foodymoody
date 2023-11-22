import { styled } from 'styled-components';
import { useInput } from 'hooks/useInput';
import { CloseSmallIcon } from '../icon/icons';
import { Input } from '../input/Input';
import { StarRating } from '../starRating/StarRating';
import { ImageBox } from './ImageBox';

type Props = {
  menuItem: FeedImage; //feedimage로 바꿔야하는지 확인
  onEditMenuName: (id: string, name: string) => void;
  onEditStarRating: (id: string, rate: number) => void;
  onRemove: (id: string) => void;
};

export const MenuItemEditor: React.FC<Props> = ({
  menuItem,
  onEditMenuName,
  onEditStarRating,
  onRemove,
}) => {
  const {
    id,
    menu: { name, rating },
  } = menuItem;

  const { value, handleChange, helperText } = useInput({
    initialValue: name,
    validator: (value) => value.trim().length > 0,
    helperText: '메뉴 이름을 입력해주세요',
  });

  const handleUploadImage = () => {};

  return (
    <Wrapper>
      <LeftContent>
        <ImageBox
          imageUrl={'https://picsum.photos/200'}
          onClick={handleUploadImage}
        />
        <ContentBody>
          <Content>
            <label htmlFor="menu">메뉴 이름</label>
            <Input
              id="menu"
              variant="ghost"
              helperText={helperText}
              value={value}
              onChangeValue={(value) => {
                handleChange(value);
              }}
              onBlur={() => {
                onEditMenuName(id, value);
              }}
            />
          </Content>
          <Content>
            <label>메뉴 별점</label>
            <StarRating
              currentRating={rating}
              onClick={(newRate) => {
                onEditStarRating(id, newRate);
              }}
            />
          </Content>
        </ContentBody>
      </LeftContent>

      <CloseSmallIcon
        onClick={() => {
          onRemove(id);
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
  border-bottom: 1px dashed ${({ theme: { colors } }) => colors.black};
  padding: 16px 0;

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
