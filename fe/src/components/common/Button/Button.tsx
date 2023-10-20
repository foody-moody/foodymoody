import styled from 'styled-components';

type Props = React.ButtonHTMLAttributes<HTMLButtonElement> & {
  size: 's' | 'l';
  backgroundColor: 'orange' | 'black' | 'white';
  children: React.ReactNode;
  width?: number;
};

export const Button: React.FC<Props> = ({
  children,
  backgroundColor,
  width,
  ...props
}) => {
  return (
    <Wrapper $backgroundColor={backgroundColor} $width={width} {...props}>
      {children}
    </Wrapper>
  );
};

const Wrapper = styled.button<{
  size: 's' | 'l';
  $backgroundColor: 'orange' | 'black' | 'white';
  $width?: number;
}>`
  font: ${({ theme: { fonts } }) => fonts.displayM16};
  height: ${({ size }) => (size === 's' ? '40px' : '56px')};
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
