import { InputHTMLAttributes } from 'react';
import { styled } from 'styled-components';

type Props = {
  type?: string;
  placeholder?: string;
  limitedLength?: number;
  onChangeValue?(value: string): void;
  onPressEnter?(): void;
  onInputFocus?(): void;
} & InputHTMLAttributes<HTMLInputElement>;

export const InputCore: React.FC<Props> = ({
  type = 'text',
  placeholder,
  limitedLength,
  onChangeValue,
  onPressEnter,
  onInputFocus,
  ...props
}) => {
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    if (limitedLength && value.length > limitedLength) return;
    onChangeValue?.(value);
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      onPressEnter?.();
    }
  };

  return (
    <>
      <Wrapper
        type={type}
        placeholder={placeholder}
        onChange={handleInputChange}
        onKeyDown={handleKeyDown}
        onFocus={onInputFocus}
        {...props}
      />
    </>
  );
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
