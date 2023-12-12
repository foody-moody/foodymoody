import React, { Children, isValidElement } from 'react';
import { styled } from 'styled-components';

type Props = {
  gap?: number;
  children?: React.ReactNode;
};

export const CenterContent: React.FC<Props> = ({ gap = 0, children }) => {
  return <CenterContentWrapper $gap={gap}>{children}</CenterContentWrapper>;
};

const CenterContentWrapper = styled.div<{
  $gap: number;
}>`
  width: 100%;
  display: flex;
  align-items: center;
  gap: ${({ $gap }) => `${$gap}px`};
`;

const InputCenterContentType = (<CenterContent />).type;

export const getInputCenterContent = (children: React.ReactNode) => {
  const childrenArray = Children.toArray(children);
  return childrenArray.find(
    (child) => isValidElement(child) && child.type === InputCenterContentType
  );
};
