import React, { Children, isValidElement } from 'react';
import { styled } from 'styled-components';

type Props = {
  gap?: number;
  children?: React.ReactNode;
};

export const RightContent: React.FC<Props> = ({ gap = 0, children }) => {
  return <RightContentWrapper $gap={gap}>{children}</RightContentWrapper>;
};

const RightContentWrapper = styled.div<{
  $gap: number;
}>`
  display: flex;
  gap: ${({ $gap }) => `${$gap}px`};
`;

const RightContentType = (<RightContent />).type;

export const getInputRightContent = (children: React.ReactNode) => {
  const childrenArray = Children.toArray(children);
  return childrenArray.find(
    (child) => isValidElement(child) && child.type === RightContentType
  );
};
