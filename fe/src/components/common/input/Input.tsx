import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { BottomPanel, getInputBottomPanel } from './InputBottomPanel';
import { CenterContent, getInputCenterContent } from './InputCenterContent';
import { HelperText, getHelperText } from './InputHelperText';
import { InnerLabel, getInputInnerLabel } from './InputInnerLabel';
import { LeftContent, getInputLeftContent } from './InputLeftContent';
import { RightContent, getInputRightContent } from './InputRightContent';
import { TopPanel, getInputTopPanel } from './InputTopPanel';

type BaseInputProps = {
  variant: 'ghost' | 'underline' | 'default' | 'comment' | 'rectangle';
  helperText?: string;
  isFocused?: boolean;
  children?: React.ReactNode;
};

export const BaseInput = ({
  variant,
  helperText,
  isFocused = false,
  children,
}: BaseInputProps) => {
  const Shape = SHAPE_VARIANT[variant];

  const inputLeftContent = getInputLeftContent(children);
  const inputRightContent = getInputRightContent(children);
  const inputInnerLabel = getInputInnerLabel(children);
  const inputCenterContent = getInputCenterContent(children);
  const inputHelperText = getHelperText(children);
  const inputTopPanel = getInputTopPanel(children);
  const inputBottomPanel = getInputBottomPanel(children);

  return (
    <Wrapper>
      {inputTopPanel && <>{inputTopPanel}</>}
      <Shape $variant={variant} $isError={!!helperText} $isFocused={isFocused}>
        {inputLeftContent && <>{inputLeftContent}</>}
        {inputInnerLabel && <>{inputInnerLabel}</>}
        {inputCenterContent && <>{inputCenterContent}</>}
        {inputRightContent && <>{inputRightContent}</>}
        {inputHelperText && <>{inputHelperText}</>}
        {inputBottomPanel && <>{inputBottomPanel}</>}
      </Shape>
    </Wrapper>
  );
};

export const Input = Object.assign(BaseInput, {
  LeftContent,
  RightContent,
  InnerLabel,
  CenterContent,
  HelperText,
  TopPanel,
  BottomPanel,
});

const Wrapper = styled.div`
  position: relative;

  aside.EmojiPickerReact {
    position: absolute;
    top: -280px;
    left: 20px;
    width: 300px !important;
    height: 270px !important;

    ${media.md} {
      width: 450px !important;
      height: 300px !important;
      top: -310px;
    }

    ${media.sm} {
      width: 470px !important;
      height: 350px !important;
      top: -360px;
    }

    ${media.xs} {
      width: 314px !important;
      height: 350px !important;
      top: -360px;
      left: 0px;
    }

    .Flex {
      display: none;
    }
  }
`;

const BaseWrapper = styled.div<{
  $variant: 'ghost' | 'underline' | 'default' | 'comment' | 'rectangle';
  $isFocused: boolean;
  $isError: boolean;
}>`
  width: 100%;
  display: flex;
  position: relative;
  box-sizing: border-box;
  background-color: ${({ theme: { colors } }) => colors.white};
  svg {
    cursor: pointer;
  }
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

const RectangleWrapper = styled(BaseWrapper)`
  border: 1px solid
    ${({ $isFocused, $isError, theme: { colors } }) =>
      $isFocused && $isError ? colors.pink : colors.black};
  border-radius: 4px;
  padding: 12px 10px;

  &:focus-within {
    border-color: ${({ $isError, theme: { colors } }) =>
      $isError ? colors.pink : colors.textTertiary};
  }
`;

const CommentWrapper = styled(BaseWrapper)`
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 12px 20px;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  border-radius: ${({ theme: { radius } }) => radius.large};
`;

const SHAPE_VARIANT = {
  ghost: GhostWrapper,
  underline: UnderLineWrapper,
  rectangle: RectangleWrapper,
  default: DefaultWrapper,
  comment: CommentWrapper,
};
