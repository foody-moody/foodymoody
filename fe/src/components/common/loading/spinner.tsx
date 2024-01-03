import styled, { keyframes } from 'styled-components';

type Props = {
  width?: number;
  height?: number;
  borderWidth?: number;
  color?: 'blue' | 'black';
  isLoading: boolean;
};

export const Spinner: React.FC<Props> = ({
  width = 16,
  height = 16,
  borderWidth = 3,
  color = 'blue',
  isLoading,
}) => {
  return (
    <>
      {isLoading && (
        <Wrapper
          $width={width}
          $height={height}
          $borderWidth={borderWidth}
          $color={color}
        />
      )}
    </>
  );
};

const spin = keyframes`
  to {
    transform: rotate(1turn);
  }
`;

const Wrapper = styled.div<{
  $width: number;
  $height: number;
  $borderWidth: number;
  $color: 'blue' | 'black';
}>`
  width: ${({ $width }) => `${$width}px`};
  height: ${({ $height }) => `${$height}px`};
  border: ${({ $borderWidth, $color, theme: { colors } }) =>
    `${$borderWidth}px solid ${
      $color === 'blue' ? colors.blue100 : colors.black
    }`};
  border-top-color: ${({ $color, theme: { colors } }) =>
    $color === 'blue' ? colors.blue500 : colors.dimDark};
  border-radius: 50%;
  animation: ${spin} 0.8s infinite ease-in-out;
`;
