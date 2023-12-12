import React, { Children, isValidElement } from 'react';
import { styled } from 'styled-components';

type Props = {
  children?: React.ReactNode;
};

export const BottomPanel: React.FC<Props> = ({ children }) => {
  return <BottomPanelWrapper>{children}</BottomPanelWrapper>;
};

const BottomPanelWrapper = styled.div`
  position: absolute;
  width: 100%;
  max-height: 400px;
  top: 100%;
  display: flex;
  flex-direction: column;
  gap: 8px;
  padding: 16px;

  background-color: ${({ theme: { colors } }) => colors.white};
  border-radius: ${({ theme: { radius } }) => radius.small};
`;

const BottomPanelType = (<BottomPanel />).type;

export const getInputBottomPanel = (children: React.ReactNode) => {
  const childrenArray = Children.toArray(children);
  return childrenArray.find(
    (child) => isValidElement(child) && child.type === BottomPanelType
  );
};
