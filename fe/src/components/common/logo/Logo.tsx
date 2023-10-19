import { styled } from 'styled-components';
import { LogoLarge, LogoSmall } from '../icons';

type Props = {
  size: 's' | 'l';
  onClick(): void;
};

export const Logo: React.FC<Props> = ({ size, onClick }) => {
  const SelectedLogo = size === 's' ? LogoSmall : LogoLarge;
  return (
    <Wrapper onClick={onClick}>
      <SelectedLogo onClick={onClick} />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  cursor: pointer;
  width: fit-content;
  height: fit-content;
`;
