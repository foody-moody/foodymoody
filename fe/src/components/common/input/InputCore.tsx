import { styled } from 'styled-components';
import { InputHTMLAttributes } from 'react';

type Props = {
  type?: string;
  placeholder?: string;
  onValueChange?(value: string): void;
  onPressEnter?(): void;
  onInputFocus?(): void;
} & InputHTMLAttributes<HTMLInputElement>;

export const InputCore: React.FC<Props> = ({ type = 'text', placeholder,onValueChange, onPressEnter,onInputFocus ,...props}) => {

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    onValueChange?.(e.target.value);
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      onPressEnter?.();
    }
  }

  return <>
    <Wrapper
      type={type}
      placeholder={placeholder}
      onChange={handleInputChange}
      onKeyDown={handleKeyDown}
      onFocus={onInputFocus}
      {...props}
    />
    </>

};
const Wrapper = styled.input`
  outline: none;
  border: none;
  width: 100%;
  background-color: transparent;
  font: ${({ theme: { fonts } }) => fonts.displayM14};

  &::placeholder {
    color: ${({ theme: { colors } }) => colors.textPlaceholder};
  }
`;

