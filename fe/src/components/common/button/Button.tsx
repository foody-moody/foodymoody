import { forwardRef } from 'react';
import styled from 'styled-components';

type Props = React.ButtonHTMLAttributes<HTMLButtonElement> & {
  size: 'xs' | 's' | 'l';
  backgroundColor: 'orange' | 'black' | 'white' | 'blue500';
  children: React.ReactNode;
  width?: number;
  shadow?: boolean;
};

export const Button = forwardRef<HTMLButtonElement, Props>(
  ({ children, backgroundColor, width, shadow = false, ...props }, ref) => {
    return (
      <Wrapper
        ref={ref}
        $backgroundColor={backgroundColor}
        $width={width}
        $shadow={shadow}
        {...props}
      >
        {children}
      </Wrapper>
    );
  }
);

const Wrapper = styled.button<{
  size: 'xs' | 's' | 'l';
  $backgroundColor: 'orange' | 'black' | 'white' | 'blue500';
  $width?: number;
  $shadow?: boolean;
}>`
  font: ${({ theme: { fonts } }) => fonts.displayM16};
  height: ${({ size }) =>
    size === 's' ? '40px' : size === 'l' ? '56px' : '32px'};
  padding-top: ${({ size }) => (size === 's' ? '8px' : '16px')};
  padding-bottom: ${({ size }) => (size === 's' ? '8px' : '16px')};
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  border-radius: ${({ theme: { radius } }) => radius.large};
  width: ${({ $width }) => ($width ? `${$width}px` : '100%')};
  background-color: ${({ $backgroundColor, theme: { colors } }) =>
    colors[$backgroundColor]};
  color: ${({ $backgroundColor, theme: { colors } }) =>
    $backgroundColor === 'black' ? colors.white : colors.black};
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  transition: all 0.3s;
  box-shadow: ${({ $shadow }) =>
    $shadow ? '2px 2px 0px 0px #0A0A0A' : 'none'};

  &:hover {
    border-color: ${({ theme: { colors } }) => colors.black};
    opacity: 0.85;
  }

  &:active {
    outline: none;
    transform: scale(0.97);
  }

  &:disabled {
    opacity: 0.55;
    cursor: default;

    &:active {
      transform: none;
    }
  }

  &:focus {
    outline: none;
  }
`;
