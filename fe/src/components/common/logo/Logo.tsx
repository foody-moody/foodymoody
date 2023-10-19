import { styled } from 'styled-components';
import { LogoLarge, LogoSmall } from '../icon/icons';
import { media } from 'styles/mediaQuery';

type Props = {
  onClick(): void;
};

export const Logo: React.FC<Props> = ({ onClick }) => {
  return (
    <Wrapper onClick={onClick}>
      <StyledSmallLogo />
      <StyledLargeLogo/>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  cursor: pointer;
`;

const StyledLargeLogo = styled(LogoLarge)`
  display: block;

  ${media.lg}{
    display: none;
  }

  ${media.xs}{
    display: block;
  }
  
`;

const StyledSmallLogo = styled(LogoSmall)`
  display: none;

  ${media.lg} {
    display: block;
  }

  ${media.xs} {
    display: none;
  }
`;