import { useState, InputHTMLAttributes } from 'react';
import { styled } from 'styled-components';
import { InputCore } from './InputCore';

type Props = {
  type?: 'password' | 'text';
  placeholder?: string;
  variant: 'ghost' | 'underline' | 'default' | 'comment';
  helperText?: string;
  onChangeValue?(value: string): void;
  onPressEnter?(): void;
} & InputHTMLAttributes<HTMLInputElement>;

export const Input: React.FC<Props> = (
  {
    type = 'text',
    placeholder = '입력해주세요',
    variant,
    helperText,
    onChangeValue,
    ...props
  }
) => {
  const [isFocused, setIsFocused] = useState(false);

  const WrapperShape = SHAPE_VARIANT[variant];
  return (
    <>
      <WrapperShape
        $variant={variant}
        $isError={!!helperText}
        $isFocused={isFocused}
      >
        {variant === 'default' && (
          <LabelText $isFocused={isFocused}>{placeholder}</LabelText>
        )}
        {variant === 'comment' && <Dummy />}
        <InputCore
          type={type}
          placeholder={variant !== 'default' ? placeholder : ''}
          onChangeValue={onChangeValue}
          onPressEnter={() => {
            console.log('press enter');
          }}
          onInputFocus={() => {
            setIsFocused(true);
          }}
          {...props}
        />
        {variant === 'comment' && <Dummy />}
        {isFocused && helperText && <HelperText>{helperText}</HelperText>}
      </WrapperShape>
    </>
  );
};

const LabelText = styled.label<{
  $isFocused: boolean;
  $value?: string;
}>`
  position: absolute;
  left: 22px;
  transition: all 0.2s ease-in-out;
  transform: ${({ $isFocused }) =>
    $isFocused
      ? 'translate(0, -12px) scale(0.75)'
      : 'translate(0, 0px) scale(1)'};
  transform-origin: top left;
  pointer-events: none;
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  color: ${({ theme: { colors } }) => colors.textPlaceholder};
`;

const BaseWrapper = styled.div<{
  $variant: 'ghost' | 'underline' | 'default' | 'comment';
  $isFocused: boolean;
  $isError: boolean;
}>`
  width: 100%;
  position: relative;
  box-sizing: border-box;
  background-color: ${({ theme: { colors } }) => colors.white};
`;

const GhostWrapper = styled(BaseWrapper)`
  border-radius: 4px;

  &:focus-within {
    background-color: ${({ theme: { colors } }) => colors.bgGray50};
  }
`;

const UnderLineWrapper = styled(BaseWrapper)`
  padding: 2px 0px;
  border-radius: 4px 4px 0px 0px;
  border-bottom: 1px solid ${({ theme: { colors } }) => colors.black};

  &:focus-within {
    background-color: ${({ theme: { colors } }) => colors.bgGray50};
  }
`;

const DefaultWrapper = styled(BaseWrapper)`
  border: 1px solid
    ${({ $isFocused, $isError, theme: { colors } }) =>
      $isFocused && $isError ? colors.pink : colors.black};
  border-radius: ${({ theme: { radius } }) => radius.large};

  padding: ${({ $isFocused }) =>
    $isFocused ? '20px 20px 4px 20px' : '12px 20px '};

  &:focus-within {
    border-color: ${({ $isError, theme: { colors } }) =>
      $isError ? colors.pink : colors.textTertiary};
  }
`;

const CommentWrapper = styled(BaseWrapper)`
  display: flex;
  gap: 8px;
  padding: 12px 20px;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  border-radius: ${({ theme: { radius } }) => radius.large};
`;

const SHAPE_VARIANT = {
  ghost: GhostWrapper,
  underline: UnderLineWrapper,
  default: DefaultWrapper,
  comment: CommentWrapper,
};

// TODO comment 인풋 - Icon교체, 버튼 교체
const Dummy = styled.div`
  width: 50px;
  height: 24px;
  background-color: ${({ theme: { colors } }) => colors.textTertiary};
`;

const HelperText = styled.div`
  position: absolute;
  top: 100%;
  right: 5%;
  font: ${({ theme: { fonts } }) => fonts.displayM10};
  color: ${({ theme: { colors } }) => colors.pink};
`;