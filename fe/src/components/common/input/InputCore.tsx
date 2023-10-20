import { styled } from 'styled-components';

type Props = {
  type?: string;
  placeholder?: string;
  onChange?(value: string): void;
  onPressEnter?(): void;
  onFocus?(): void;
};

export const InputCore: React.FC<Props> = ({ type = 'text', placeholder,onChange, onPressEnter,onFocus }) => {

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    onChange?.(e.target.value);
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
      onFocus={onFocus}
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

