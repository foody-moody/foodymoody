import React, { useRef } from 'react';
import { styled } from 'styled-components';
import { useAnimation } from 'hooks/useAnimation';
import { useDropdown } from 'hooks/useDropdown';

type Props = {
  opener: React.ReactNode;
  align?: 'left' | 'right';
  children: React.ReactNode;
};

export const Dropdown: React.FC<Props> = (
  { opener, align = 'left', children }
) => {
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
  top: 100%;
  ${({ $align }) => $align}: 0;
  background-color: ${({ theme }) => theme.colors.white};
  transform: ${({ $animationTrigger }) =>
    $animationTrigger ? 'translateY(0);' : 'translateY(-10px);'};

  transition: 0.2s ease;
  border-radius: 0px 0px 40px 0px;
  border: 1px solid ${({ theme }) => theme.colors.black};
  overflow: hidden;
`;

const Opener = styled.div`
  display: flex;

  width: fit-content;
  height: fit-content;
  align-items: center;

  cursor: pointer;
  border-radius: 4px;

  &:hover {
    background: ${({ theme: { colors } }) => colors.bgGray50};
  }
`;
