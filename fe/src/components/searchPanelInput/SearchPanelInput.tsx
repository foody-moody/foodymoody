import { useState } from 'react';
import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import { SearchIcon } from 'components/common/icon/icons';
import { Input2 } from 'components/common/input/Input2';
import { InputField } from 'components/common/input/InputField';

type Props = {
  variant: 'ghost' | 'underline' | 'default' | 'comment' | 'rectangle';
  value?: string;
  helperText?: string;
  onChangeValue?(value: string): void;
};

export const SearchPanelInput: React.FC<Props> = ({
  variant,
  value,
  helperText,
  onChangeValue,
}) => {
  const [isOpen, setIsOpen] = useState(false);
  // TODO panel 데이터 페치

  const handlePanelOpen = () => {
    // TODO 데이터가 있으면 &&
    setIsOpen(true);
  };

  const handlePanelClose = () => {
    setIsOpen(false);
  };

  const handleSearch = () => {
    console.log('검색');
  };

  return (
    <Wrapper>
      <Input2 variant={variant} helperText={helperText}>
        <Input2.CenterContent>
          <InputField
            value={value}
            onChangeValue={onChangeValue}
            onInputFocus={handlePanelOpen}
            onBlur={handlePanelClose}
          />
        </Input2.CenterContent>
        <Input2.RightContent>
          <TextButton size="m" color="black" onClick={handleSearch}>
            <SearchIcon />
          </TextButton>
        </Input2.RightContent>
        <Input2.BottomPanel isOpen={isOpen}>
          {Array.from({ length: 3 }).map((_, index) => (
            <div key={index} style={{ width: '50px', background: 'red' }}>
              하하하{index}
            </div>
          ))}
        </Input2.BottomPanel>
      </Input2>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
`;
