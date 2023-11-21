import { styled } from 'styled-components';
import { useLockBodyScroll } from 'hooks/useLockBodyScroll';
import { CloseLargeIcon } from '../icon/icons';

type Props = {
  onClick: () => void;
};

export const Dim: React.FC<Props> = ({ onClick }) => {
  useLockBodyScroll(true);

  return (
    <Wrapper onClick={onClick}>
      <CloseBtn />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100dvh;
  z-index: 100;
  background-color: ${({ theme: { colors } }) => colors.dimDark};
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
