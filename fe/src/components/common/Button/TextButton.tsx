import styled from 'styled-components';

type Props = React.ButtonHTMLAttributes<HTMLButtonElement> & {
  color: 'orange' | 'black';
  children: React.ReactNode;
  size?: 's' | 'm' | 'l';
};

export const TextButton: React.FC<Props> = ({
  color,
  children,
  size = 'l',
  ...props
}) => {
  return (
    <Wrapper $color={color} $size={size} {...props}>
      {children}
    </Wrapper>
  );
};

const Wrapper = styled.button<{
  $color: 'orange' | 'black';
  $size: 's' | 'm' | 'l';
}>`
  font: ${({ $size, theme: { fonts } }) =>
    $size === 's'
      ? fonts.displayM12
      : $size === 'm'
      ? fonts.displayM14
      : fonts.displayM16};
  color: ${({ $color, theme: { colors } }) => colors[$color]};
  width: fit-content;
  white-space: nowrap;
  background-color: transparent;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  transition: all 0.3s;

  &:hover {
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
