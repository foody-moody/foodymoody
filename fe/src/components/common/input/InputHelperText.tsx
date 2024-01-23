import React, { Children, isValidElement } from 'react';
import { styled } from 'styled-components';

type Props = {
  isError?: boolean;
  isFocused?: boolean;
  align?: 'left' | 'right';
  alignValue?: string;
  helperType?: 'success' | 'error';
  children?: React.ReactNode;
};

export const HelperText: React.FC<Props> = ({
  isError,
  isFocused = false,
  align = 'left',
  alignValue = '5%',
  helperType,
  children,
}) => {
  return (
    <>
      {isFocused && isError && (
        <HelperTextWrapper
          $align={align}
          $alignValue={alignValue}
          $helperType={helperType}
        >
          {children}
        </HelperTextWrapper>
      )}
    </>
  );
};

const HelperTextWrapper = styled.div<{
  $align: 'left' | 'right';
  $alignValue: string;
  $helperType?: 'success' | 'error';
}>`
  position: absolute;
  top: 100%;
  ${({ $align, $alignValue }) =>
    $align === 'left' ? `left: ${$alignValue};` : `right: ${$alignValue};`}
  font: ${({ theme: { fonts } }) => fonts.displayM10};
  color: ${({ theme: { colors }, $helperType }) =>
    $helperType === 'success' ? colors.green : colors.pink};
`;

const HelperTextType = (<HelperText />).type;

export const getHelperText = (children: React.ReactNode) => {
  const childrenArray = Children.toArray(children);
  return childrenArray.find(
    (child) => isValidElement(child) && child.type === HelperTextType
  );
};
