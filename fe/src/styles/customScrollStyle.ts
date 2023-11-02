import { css } from 'styled-components';

export const customScrollStyle = css`
  &::-webkit-scrollbar {
    width: 10px;
    background-color: transparent;

    &-button {
      width: 0;
      height: 0;
    }

    &-thumb {
      width: 4px;
      border-radius: 10px;
      background-color: ${({ theme: { colors } }) => colors.textTertiary};
      border: 3px solid ${({ theme: { colors } }) => colors.white};
    }

    &-track {
      background-color: transparent;
    }
  }
`;
