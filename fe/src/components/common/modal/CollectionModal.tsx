import { useState } from 'react';
import styled from 'styled-components';
import { StoreMoodSelector } from '../badge/StoreMoodSelector';
import { Button } from '../button/Button';
import { Dim } from '../dim/Dim';
import { PlusSquareIcon } from '../icon/icons';
import { Input } from '../input/Input';
import { InputField } from '../input/InputField';
import { useModal } from './useModal';

export const CollectionModal = () => {
  const { openModal, closeModal } = useModal<'collection'>();
  const [selectedBadgeList, setSelectedBadgeList] = useState<Badge[]>([]);
  const [isFormToggle, setIsFormToggle] = useState(false);
  // storeMood: selectedBadgeList.map((badge) => badge.id),

  const handleSelectBadgeList = (badges: Badge[]) => {
    setSelectedBadgeList(badges);
  };

  return (
    <>
      <Dim isTransparent onClick={() => closeModal('collection')} />
      <Wrapper>
        <MyList>
          <h2>나만의 컬렉션</h2>

          {/* TODO. API 완료 되면연동 해야함 */}
          <p>아직 회원님의 컬렉션이 없어요</p>
        </MyList>

        {!isFormToggle && (
          <OpenFormBtn onClick={() => setIsFormToggle(!isFormToggle)}>
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
            <SubmitBtn
              size="s"
              shadow
              backgroundColor="blue500"
              width={120}
              type="submit"
            >
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
  }

  p {
    font: ${({ theme: { fonts } }) => fonts.displayM16};
    color: ${({ theme: { colors } }) => colors.textSecondary};
    margin-bottom: 16px;
  }

  border-bottom: 1px dashed black;
`;

const OpenFormBtn = styled.button`
  display: flex;
  gap: 8px;
  align-items: center;
  font: ${({ theme: { fonts } }) => fonts.displayM16};
`;

const SubTitle = styled.h3`
  font: ${({ theme: { fonts } }) => fonts.displayB16};
`;

const Form = styled.form`
  display: flex;
  flex-direction: column;
  gap: 16px;

  > div {
    display: flex;
    flex-direction: column;
    gap: 4px;
  }
`;

const SubmitBtn = styled(Button)`
  margin-left: auto;
`;
