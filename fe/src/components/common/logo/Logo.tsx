import { styled } from 'styled-components';
import { LogoLarge, LogoSmall } from '../icons';

type Props = {
  size: 's' | 'l';
  onClick(): void;
};

export const Logo: React.FC<Props> = ({ size, onClick }) => {
  const SelectedLogo = size === 's' ? SmallLogo : LargeLogo;
  return <SelectedLogo onClick={onClick} />;
};

const LargeLogo = styled(LogoLarge)`
  cursor: pointer;
`;

const SmallLogo = styled(LogoSmall)`
  cursor: pointer;
`;
