import React, { Children, isValidElement } from 'react';
import { styled } from 'styled-components';

type Props = {
  gap?: number;
  children?: React.ReactNode;
};

export const LeftContent: React.FC<Props> = ({ gap = 0, children }) => {
  return <LeftContentWrapper $gap={gap}>{children}</LeftContentWrapper>;
};

const LeftContentWrapper = styled.div<{
  $gap: number;
}>`
  display: flex;
  gap: ${({ $gap }) => `${$gap}px`};
`;

const LeftContentType = (<LeftContent />).type;

export const getInputLeftContent = (children: React.ReactNode) => {
  const childrenArray = Children.toArray(children);
  return childrenArray.find(
    (child) => isValidElement(child) && child.type === LeftContentType
  );
};
