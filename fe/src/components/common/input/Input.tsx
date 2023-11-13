import { useState } from 'react';
import { styled } from 'styled-components';
import { BellIcon } from '../icon/icons';
import { InputCore } from './InputCore';

type Props = {
  type?: 'password' | 'text';
  placeholder?: string;
  variant: 'ghost' | 'underline' | 'default' | 'comment';
  helperText?: string;
  onChange?(value: string): void;
  onPressEnter?(): void;
};

export const Input: React.FC<Props> = (
  {
    type = 'text',
    placeholder = '입력해주세요',
    variant,
    helperText,
    onChange,
    onPressEnter,
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
        {variant === 'comment' && <BellIcon />}
        <InputCore
          type={type}
          placeholder={variant !== 'default' ? placeholder : ''}
          onChange={onChange}
          onPressEnter={() => {
            console.log('press enter');
          }}
          onFocus={() => {
            setIsFocused(true);
          }}
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
  top: 103%;
  right: 0;
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  color: ${({ theme: { colors } }) => colors.pink};
`;
