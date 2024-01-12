import { useState } from 'react';
import styled from 'styled-components';
import { customScrollStyle } from 'styles/customStyle';
import { StoreMoodSelector } from '../badge/StoreMoodSelector';
import { Button } from '../button/Button';
import { Checkbox } from '../checkbox/CheckBox';
import { Dim } from '../dim/Dim';
import { PlusSquareIcon } from '../icon/icons';
import { Input } from '../input/Input';
import { InputField } from '../input/InputField';
import { useModal } from './useModal';

const DATA = [
  {
    id: '1',
    title: '컬렉션 제목1',
    checked: false,
  },
  {
    id: '2',
    title: '컬렉션 제목2',
    checked: true,
  },
  {
    id: '3',
    title: '컬렉션 제목2',
    checked: true,
  },
  {
    id: '4',
    title: '컬렉션 제목2',
    checked: true,
  },
  {
    id: '5',
    title: '컬렉션 제목2',
    checked: true,
  },
  {
    id: '6',
    title: '컬렉션 제목2',
    checked: true,
  },
];
// const DATA = [

// ];

export const CollectionModal: React.FC<CollectionModalProps> = ({
  // data,
  type = 'default',
}) => {
  const {
    //  openModal,
    closeModal,
  } = useModal<'collection'>();
  const [selectedBadgeList, setSelectedBadgeList] = useState<Badge[]>([]);
  const [isFormToggle, setIsFormToggle] = useState(false);
  const [checkedIds, setCheckedIds] = useState(
    DATA ? DATA.filter((item) => item.checked).map((item) => item.id) : []
  );
  const [isPrivate, setIsPrivate] = useState(false);

  const handleSelectBadgeList = (badges: Badge[]) => {
    setSelectedBadgeList(badges);
  };

  const handleCheckChange = (id: string, isChecked: boolean) => {
    setCheckedIds((prevId) => {
      if (isChecked) {
        return [...prevId, id];
      } else {
        return prevId.filter((checkedId) => checkedId !== id);
      }
    });
  };

  const handleOpenForm = () => {
    setIsFormToggle(!isFormToggle);
  };

  console.log(checkedIds);
  // const hand = () => {
  //   console.log(checkedIds);
  // };

  return (
    <>
      <Dim isTransparent onClick={() => closeModal('collection')} />
      <Wrapper>
        <MyList>
          {/* <button onClick={hand}>확인</button> */}
          <h2>나만의 컬렉션</h2>

          {/* TODO. API 완료 되면연동 해야함 */}
          {DATA.length === 0 && <Text>아직 회원님의 컬렉션이 없어요</Text>}

          <ListBox $isFormToggle={isFormToggle}>
            {DATA.map((collection) => (
              <List>
                {!isFormToggle && type === 'add' && (
                  <Checkbox
                    key={collection.id}
                    id={collection.id}
                    checked={collection.checked}
                    onCheckChange={handleCheckChange}
                  />
                )}
                <Title>{collection.title}</Title>
              </List>
            ))}
          </ListBox>
        </MyList>

        {!isFormToggle && (
          <OpenFormBtn onClick={handleOpenForm}>
            <PlusSquareIcon />
            새로운 컬렉션 만들기
          </OpenFormBtn>
        )}

        {isFormToggle && (
          <Form>
            <div>
              <SubTitle>컬렉션 제목</SubTitle>
              <Input variant="underline">
                <Input.CenterContent>
                  <InputField
                    placeholder="제목을 입력해주세요"
                    // value={value}
                    // onChangeValue={onChangeValue}
                    // onInputFocus={handlePanelOpen}
                    // onBlur={handlePanelClose}
                  />
                </Input.CenterContent>
              </Input>
            </div>

            <div>
              <SubTitle>컬렉션 설명</SubTitle>
              <Input variant="underline">
                <Input.CenterContent>
                  <InputField
                    placeholder="설명을 입력해주세요 (선택 사항)"
                    // value={value}
                    // onChangeValue={onChangeValue}
                    // onInputFocus={handlePanelOpen}
                    // onBlur={handlePanelClose}
                  />
                </Input.CenterContent>
              </Input>
            </div>

            <div>
              <SubTitle>컬렉션 무드</SubTitle>

              <StoreMoodSelector
                selectedBadges={selectedBadgeList}
                onSelectedBadges={handleSelectBadgeList}
              />
            </div>

            <Privacy>
              <Button
                size="xs"
                backgroundColor={isPrivate ? 'black' : 'white'}
                onClick={() => setIsPrivate(!isPrivate)}
                type="button"
              >
                비공개
              </Button>
              <Button
                size="xs"
                backgroundColor={isPrivate ? 'white' : 'black'}
                onClick={() => setIsPrivate(!isPrivate)}
                type="button"
              >
                공개
              </Button>
            </Privacy>

            <SubmitBtn size="s" shadow backgroundColor="blue500" width={120}>
              만들기
            </SubmitBtn>
          </Form>
        )}
      </Wrapper>
    </>
  );
};

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 16px;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  background: ${({ theme: { colors } }) => colors.white};
  padding: 16px;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 100;
  max-width: 375px;
  min-width: 343px;
  width: 100%;
  height: fit-content;
`;

const MyList = styled.div`
  h2 {
    font: ${({ theme: { fonts } }) => fonts.displayB20};
    margin-bottom: 4px;
  }

  border-bottom: 1px dashed black;
  padding-bottom: 8px;
`;

const Text = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM16};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;

const OpenFormBtn = styled.button`
  display: flex;
  gap: 8px;
  align-items: center;
  font: ${({ theme: { fonts } }) => fonts.displayM16};
`;

const SubTitle = styled.h3`
  font: ${({ theme: { fonts } }) => fonts.displayB16};
  margin-bottom: 4px;
`;

const Form = styled.form`
  display: flex;
  flex-direction: column;
  gap: 16px;
`;

const SubmitBtn = styled(Button)`
  margin-left: auto;
`;

const Title = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  margin-bottom: 4px;
`;

const List = styled.div`
  display: flex;
  flex-direction: row;
`;

const ListBox = styled.ul<{
  $isFormToggle: boolean;
}>`
  max-height: ${({ $isFormToggle }) => ($isFormToggle ? '90px' : 'none')};
  overflow-y: ${({ $isFormToggle }) => ($isFormToggle ? 'auto' : 'none')};
  height: 100%;

  ${customScrollStyle}
`;

const Privacy = styled.div`
  display: flex;
  flex-direction: row;
  width: 100%;
  gap: 8px;
  > button {
    flex: 1;
  }
`;
