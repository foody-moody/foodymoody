import React, { Children, isValidElement } from 'react';

type Props = {
  isOpen?: boolean;
  children?: React.ReactNode;
};

export const TopPanel: React.FC<Props> = ({ isOpen = false, children }) => {
  return <>{isOpen && <>{children}</>}</>;
};

const TopPanelType = (<TopPanel />).type;

export const getInputTopPanel = (children: React.ReactNode) => {
  const childrenArray = Children.toArray(children);
  return childrenArray.find(
    (child) => isValidElement(child) && child.type === TopPanelType
  );
};
