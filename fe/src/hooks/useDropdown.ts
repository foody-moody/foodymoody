import { useState, useEffect } from 'react';

type PropsType = {
  dropdownRef?: React.RefObject<HTMLElement>;
  openerRef?: React.RefObject<HTMLElement>;
  initialValue?: boolean;
};

export const useDropdown = ({ dropdownRef, openerRef, initialValue = false }: PropsType = {} ) => {

  const [isOpen, setIsOpen] = useState(initialValue);

  const handleToggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {

      const target = event.target as Node;

      if (openerRef?.current && openerRef.current.contains(target)) {
        return;
      }

      if (dropdownRef?.current && !dropdownRef.current.contains(target)) {
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
