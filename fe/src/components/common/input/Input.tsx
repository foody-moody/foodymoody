import { useState } from 'react';
import { styled } from 'styled-components';
import { BellIcon } from '../icon/icons';
import { InputCore } from './InputCore';

type Props = {
  type?: 'password' | 'text';
  placeholder?: string;
  onChange?(value: string): void;
  variant: 'ghost' | 'underline' | 'default' | 'comment';
  helperText?: string;
};

export const Input: React.FC<Props> = (
  { type = 'text', placeholder = '입력해주세요', onChange, variant, helperText }
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
        {variant === 'comment' && <BellIcon />}
        <InputCore
          type={type}
          placeholder={placeholder}
          isFocused={isFocused}
          onChange={onChange}
          onPressEnter={() => {
            console.log('press enter');
          }}
          onFocus={() => {
            setIsFocused(true);
          }}
          helperText={helperText}
        />
        {variant === 'comment' && <Dummy />}
      </WrapperShape>
    </>
  );
};

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
  padding: 0 8px;
  border-radius: 4px;

  &:focus-within {
    background-color: rgba(166, 166, 166, 0.08);
  }
`;

const UnderLineWrapper = styled(BaseWrapper)`
  padding: 2px 8px;
  border-radius: 4px 4px 0px 0px;

  border-bottom: 1px solid ${({ theme: { colors } }) => colors.black};
  &:focus-within {
    background-color: rgba(166, 166, 166, 0.08);
  }
`;

const DefaultWrapper = styled(BaseWrapper)`
  display: flex;

  border: 1px solid
    ${({ $isFocused, $isError, theme: { colors } }) =>
      $isFocused && $isError ? colors.pink : colors.black};
  border-radius: ${({ theme: { radius } }) => radius.large};
  padding: 12px 16px 12px 16px;

  &:focus-within {
    border-color: ${({ $isError, theme: { colors } }) =>
      $isError ? colors.pink : colors.textTertiary};
  }
`;

const SHAPE_VARIANT = {
  ghost: GhostWrapper,
  underline: UnderLineWrapper,
  default: DefaultWrapper,
  comment: DefaultWrapper,
};

const Dummy = styled.div`
  width: 50px;
  height: 24px;
  background-color: ${({ theme: { colors } }) => colors.textTertiary};
`;
