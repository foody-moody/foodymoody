import React, { Children, isValidElement } from 'react';
import { styled } from 'styled-components';

type Props = {
  isError?: boolean;
  isFocused?: boolean;
  align?: 'left' | 'right';
  alignValue?: string;
  children?: React.ReactNode;
};

export const HelperText: React.FC<Props> = ({
  isError,
  isFocused = false,
  align = 'left',
  alignValue = '5%',
  children,
}) => {
  return (
    <>
      {isFocused && isError && (
        <HelperTextWrapper $align={align} $alignValue={alignValue}>
          {children}
        </HelperTextWrapper>
      )}
    </>
  );
};

const HelperTextWrapper = styled.div<{
  $align: 'left' | 'right';
  $alignValue: string;
}>`
  position: absolute;
  top: 100%;
  ${({ $align, $alignValue }) =>
    $align === 'left' ? `left: ${$alignValue};` : `right: ${$alignValue};`}
  font: ${({ theme: { fonts } }) => fonts.displayM10};
  color: ${({ theme: { colors } }) => colors.pink};
`;

const HelperTextType = (<HelperText />).type;

export const getHelperText = (children: React.ReactNode) => {
  const childrenArray = Children.toArray(children);
  return childrenArray.find(
    (child) => isValidElement(child) && child.type === HelperTextType
  );
};
