import { useState, useEffect } from 'react';

type PropsType = {
  dropdownRef?: React.RefObject<HTMLElement>;
  openerRef?: React.RefObject<HTMLElement>;
  initialValue?: boolean;
};

export const useDetectClose = ({ dropdownRef, openerRef, initialValue = false }: PropsType = {} ) => {

  const [isOpen, setIsOpen] = useState(initialValue);

  const handleToggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (openerRef?.current && openerRef.current.contains(event.target as Node)) {
        return;
      }

      if (dropdownRef?.current && !dropdownRef.current.contains(event.target as Node)) {
        setIsOpen(!isOpen)
      }
    };

    if (isOpen) {
      document.addEventListener('click', handleClickOutside);
    }

    return () => {
      document.removeEventListener('click', handleClickOutside);
    };
  }, [isOpen, openerRef, dropdownRef]);

  return { isOpen, handleToggleDropdown};
};
