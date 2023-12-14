import { useState } from 'react';
import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import { SearchIcon } from 'components/common/icon/icons';
import { Input } from 'components/common/input/Input';
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
      <Input variant={variant} helperText={helperText}>
        <Input.CenterContent>
          <InputField
            value={value}
            onChangeValue={onChangeValue}
            onInputFocus={handlePanelOpen}
            onBlur={handlePanelClose}
          />
        </Input.CenterContent>
        <Input.RightContent>
          <TextButton size="m" color="black" onClick={handleSearch}>
            <SearchIcon />
          </TextButton>
        </Input.RightContent>
        <Input.BottomPanel isOpen={isOpen}>
          {Array.from({ length: 3 }).map((_, index) => (
            <div key={index} style={{ width: '50px', background: 'red' }}>
              하하하{index}
            </div>
          ))}
        </Input.BottomPanel>
      </Input>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
`;
