import { styled } from 'styled-components';
import { useLockBodyScroll } from 'hooks/useLockBodyScroll';
import { CloseLargeIcon } from '../icon/icons';

type Props = {
  isTransparent?: boolean;
  onClick: () => void;
};

export const Dim: React.FC<Props> = ({ isTransparent = false, onClick }) => {
  useLockBodyScroll(true);

  return (
    <Wrapper $isTransparent={isTransparent} onClick={onClick}>
      <CloseBtn />
    </Wrapper>
  );
};

const Wrapper = styled.div<{
  $isTransparent?: boolean;
}>`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100dvh;
  z-index: 100;
  background-color: ${({ $isTransparent, theme: { colors } }) =>
    $isTransparent ? 'transparent' : colors.dimDark};
`;

const CloseBtn = styled(CloseLargeIcon)`
  position: absolute;
  right: 16px;
  top: 16px;

  svg {
    width: 32px;
    height: 32px;
  }
`;
