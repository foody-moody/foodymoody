import { styled } from 'styled-components';
import { LogoLarge, LogoSmall } from '../icons';

type Props = {
  size: 's' | 'l';
  onClick(): void;
};

export const Logo: React.FC<Props> = ({ size, onClick }) => {
  const SelectedLogo = size === 's' ? LogoSmall : LogoLarge;
  return (
    <LogoWrapper onClick={onClick}>
      <SelectedLogo />
    </LogoWrapper>
  );
};

const LogoWrapper = styled.div`
  cursor: pointer;
  width: fit-content;
`;
