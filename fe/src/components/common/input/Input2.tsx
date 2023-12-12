// import React, { Children, isValidElement } from 'react';
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

// type LeftContentProps = {
//   gap?: number;
//   children?: React.ReactNode;
// };

// const LeftContent: React.FC<LeftContentProps> = ({ gap = 0, children }) => {
//   return <LeftContentWrapper $gap={gap}>{children}</LeftContentWrapper>;
// };

// const LeftContentWrapper = styled.div<{
//   $gap: number;
// }>`
//   display: flex;
//   gap: ${({ $gap }) => `${$gap}px`};
// `;

// const LeftContentType = (<LeftContent />).type;

// function getInputLeftContent(children: React.ReactNode) {
//   const childrenArray = Children.toArray(children);
//   return childrenArray.find(
//     (child) => isValidElement(child) && child.type === LeftContentType
//   );
// }

// type RightContentProps = {
//   gap?: number;
//   children?: React.ReactNode;
// };

// const RightContent: React.FC<RightContentProps> = ({ gap = 0, children }) => {
//   return <RightContentWrapper $gap={gap}>{children}</RightContentWrapper>;
// };

// const RightContentWrapper = styled.div<{
//   $gap: number;
// }>`
//   display: flex;
//   border: 1px solid red;
//   gap: ${({ $gap }) => `${$gap}px`};
// `;

// const RightContentType = (<RightContent />).type;

// function getInputRightContent(children: React.ReactNode) {
//   const childrenArray = Children.toArray(children);
//   return childrenArray.find(
//     (child) => isValidElement(child) && child.type === RightContentType
//   );
// }

// type CoreProps = {
//   children?: React.ReactNode;
// };

// const Core: React.FC<CoreProps> = ({ children }) => {
//   return <>{children}</>;
// };

// const InputCoreType = (<Core />).type;

// function getInputCore(children: React.ReactNode) {
//   const childrenArray = Children.toArray(children);
//   return childrenArray.find(
//     (child) => isValidElement(child) && child.type === InputCoreType
//   );
// }

// type TopPanelProps = {
//   children?: React.ReactNode;
// };

// const TopPanel: React.FC<TopPanelProps> = ({ children }) => {
//   return <>{children}</>;
// };

// const TopPanelType = (<TopPanel />).type;

// function getInputTopPanel(children: React.ReactNode) {
//   const childrenArray = Children.toArray(children);
//   return childrenArray.find(
//     (child) => isValidElement(child) && child.type === TopPanelType
//   );
// }

// type BottomPanelProps = {
//   children?: React.ReactNode;
// };

// const BottomPanel: React.FC<BottomPanelProps> = ({ children }) => {
//   return <BottomPanelWrapper>{children}</BottomPanelWrapper>;
// };

// const BottomPanelWrapper = styled.div`
//   position: absolute;
//   width: 100%;
//   max-height: 400px;
//   top: 100%;
//   display: flex;
//   flex-direction: column;
//   gap: 8px;
//   padding: 16px;

//   background-color: ${({ theme: { colors } }) => colors.white};
//   border-radius: ${({ theme: { radius } }) => radius.small};
// `;

// const BottomPanelType = (<BottomPanel />).type;

// function getInputBottomPanel(children: React.ReactNode) {
//   const childrenArray = Children.toArray(children);
//   return childrenArray.find(
//     (child) => isValidElement(child) && child.type === BottomPanelType
//   );
// }

// type InnerLabelProps = {
//   isFocused?: boolean;
//   children?: React.ReactNode;
// };

// const InnerLabel: React.FC<InnerLabelProps> = ({
//   isFocused = false,
//   children,
// }) => {
//   return (
//     <InnerLabelWrapper $isFocused={isFocused}>{children}</InnerLabelWrapper>
//   );
// };

// const InnerLabelWrapper = styled.label<{
//   $isFocused: boolean;
// }>`
//   position: absolute;
//   left: 22px;
//   transition: all 0.2s ease-in-out;
//   transform: ${({ $isFocused }) =>
//     $isFocused
//       ? 'translate(0, -12px) scale(0.75)'
//       : 'translate(0, 0px) scale(1)'};
//   transform-origin: top left;
//   pointer-events: none;
//   font: ${({ theme: { fonts } }) => fonts.displayM14};
//   color: ${({ theme: { colors } }) => colors.textPlaceholder};
// `;

// const InnerLabelType = (<InnerLabel />).type;

// function getInputInnerLabel(children: React.ReactNode) {
//   const childrenArray = Children.toArray(children);
//   return childrenArray.find(
//     (child) => isValidElement(child) && child.type === InnerLabelType
//   );
// }

// type HelperTextProps = {
//   isError?: boolean;
//   isFocused?: boolean;
//   align?: 'left' | 'right';
//   alignValue?: string;
//   children?: React.ReactNode;
// };

// const HelperText: React.FC<HelperTextProps> = ({
//   isError,
//   isFocused = false,
//   align = 'left',
//   alignValue = '5%',
//   children,
// }) => {
//   return (
//     <>
//       {isFocused && isError && (
//         <HelperTextWrapper $align={align} $alignValue={alignValue}>
//           {children}
//         </HelperTextWrapper>
//       )}
//     </>
//   );
// };

// const HelperTextWrapper = styled.div<{
//   $align: 'left' | 'right';
//   $alignValue: string;
// }>`
//   position: absolute;
//   top: 100%;
//   ${({ $align, $alignValue }) =>
//     $align === 'left' ? `left: ${$alignValue};` : `right: ${$alignValue};`}
//   font: ${({ theme: { fonts } }) => fonts.displayM10};
//   color: ${({ theme: { colors } }) => colors.pink};
// `;
// const HelperTextType = (<HelperText />).type;

// function getHelperText(children: React.ReactNode) {
//   const childrenArray = Children.toArray(children);
//   return childrenArray.find(
//     (child) => isValidElement(child) && child.type === HelperTextType
//   );
// }

export const Input2 = Object.assign(BaseInput, {
  LeftContent,
  RightContent,
  InnerLabel,
  CenterContent,
  HelperText,
  TopPanel,
  BottomPanel,
});

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
  padding: ${({ $isFocused }) =>
    $isFocused ? '20px 20px 4px 20px' : '12px 20px '};

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
