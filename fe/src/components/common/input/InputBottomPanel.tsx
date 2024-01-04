import React, { Children, isValidElement } from 'react';
import { styled } from 'styled-components';
import { customScrollStyle } from 'styles/customStyle';

type Props = {
  isOpen?: boolean;
  children?: React.ReactNode;
};

export const BottomPanel: React.FC<Props> = ({ isOpen = false, children }) => {
  return <>{isOpen && <BottomPanelWrapper>{children}</BottomPanelWrapper>}</>;
};

const BottomPanelWrapper = styled.ul`
  position: absolute;
  width: 100%;
  height: 400px;
  max-height: 400px;
  top: 110%;
  display: flex;
  flex-direction: column;
  gap: 8px;
  padding: 8px;
  z-index: 100;

  box-shadow: 2px 2px rgba(0, 0, 0, 1);

  background-color: ${({ theme: { colors } }) => colors.white};
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  border-radius: 4px;

  overflow: auto;
  overscroll-behavior: contain;
  ${customScrollStyle}
`;

const BottomPanelType = (<BottomPanel />).type;

export const getInputBottomPanel = (children: React.ReactNode) => {
  const childrenArray = Children.toArray(children);
  return childrenArray.find(
    (child) => isValidElement(child) && child.type === BottomPanelType
  );
};
