import React, { Children, isValidElement } from 'react';

type Props = {
  children?: React.ReactNode;
};

export const TopPanel: React.FC<Props> = ({ children }) => {
  return <>{children}</>;
};

const TopPanelType = (<TopPanel />).type;

export const getInputTopPanel = (children: React.ReactNode) => {
  const childrenArray = Children.toArray(children);
  return childrenArray.find(
    (child) => isValidElement(child) && child.type === TopPanelType
  );
};
