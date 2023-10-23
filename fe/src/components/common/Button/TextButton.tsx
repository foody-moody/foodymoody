import styled from 'styled-components';

type Props = React.ButtonHTMLAttributes<HTMLButtonElement> & {
  color: 'orange' | 'black';
  children: React.ReactNode;
};

export const TextButton: React.FC<Props> = ({ color, children, ...props }) => {
  return (
    <Wrapper $color={color} {...props}>
      {children}
    </Wrapper>
  );
};

const Wrapper = styled.button<{
  $color: 'orange' | 'black';
}>`
  font: ${({ theme: { fonts } }) => fonts.displayM16};
  color: ${({ $color, theme: { colors } }) => colors[$color]};
  width: fit-content;
  background-color: transparent;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  transition: all 0.3s;

  &:hover {
    border-color: ${({ theme: { colors } }) => colors.black};
    opacity: 0.85;
  }

  &:disabled {
    opacity: 0.55;
    cursor: default;
  }

  &:focus {
    outline: none;
  }
`;
