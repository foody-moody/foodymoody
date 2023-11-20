import React, { useRef } from 'react';
import { styled } from 'styled-components';
import { useAnimation } from 'hooks/useAnimation';
import { useDropdown } from 'hooks/useDropdown';

type Props = {
  opener: React.ReactNode;
  align?: 'left' | 'right';
  children: React.ReactNode;
};

export const Dropdown: React.FC<Props> = ({
  opener,
  align = 'left',
  children,
}) => {
  const dropdownRef = useRef<HTMLUListElement>(null);
  const openerRef = useRef<HTMLDivElement>(null);
  const { isOpen, handleToggleDropdown } = useDropdown({
    dropdownRef,
    openerRef,
  });

  const { shouldRender, handleTransitionEnd, animationTrigger } =
    useAnimation(isOpen);

  return (
    <Wrapper>
      <Opener onClick={handleToggleDropdown} ref={openerRef}>
        {opener}
      </Opener>
      {shouldRender && (
        <DropdownBox
          ref={dropdownRef}
          $align={align}
          $animationTrigger={animationTrigger}
          onClick={handleToggleDropdown}
          onTransitionEnd={handleTransitionEnd}
        >
          {children}
        </DropdownBox>
      )}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: fit-content;
  position: relative;
`;

const DropdownBox = styled.ul<{
  $align: 'left' | 'right';
  $animationTrigger: boolean;
}>`
  display: flex;
  flex-direction: column;
  width: 270px;
  position: absolute;
  top: 102%;
  ${({ $align }) => $align}: 0;
  background-color: ${({ theme: { colors } }) => colors.white};
  transform: ${({ $animationTrigger }) =>
    $animationTrigger ? 'translateY(0);' : 'translateY(-5px);'};
  transition: 0.3s ease-in-out;
  border-radius: 0px 0px 40px 0px;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  overflow: hidden;
  z-index: 50;
`;

const Opener = styled.div`
  display: flex;
  align-items: center;
  width: fit-content;
  height: fit-content;
  cursor: pointer;
  border-radius: 4px;
`;
