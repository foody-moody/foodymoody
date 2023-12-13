import React, { Children, isValidElement } from 'react';
import { styled } from 'styled-components';

type Props = {
  isFocused?: boolean;
  children?: React.ReactNode;
};

export const InnerLabel: React.FC<Props> = ({
  isFocused = false,
  children,
}) => {
  return (
    <InnerLabelWrapper $isFocused={isFocused}>{children}</InnerLabelWrapper>
  );
};

const InnerLabelWrapper = styled.label<{
  $isFocused: boolean;
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

const InnerLabelType = (<InnerLabel />).type;

export const getInputInnerLabel = (children: React.ReactNode) => {
  const childrenArray = Children.toArray(children);
  return childrenArray.find(
    (child) => isValidElement(child) && child.type === InnerLabelType
  );
};
