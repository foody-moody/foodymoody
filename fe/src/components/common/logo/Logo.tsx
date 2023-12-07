import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { LogoLarge, LogoSmall, SantaHat } from '../icon/icons';

type Props = {
  onClick(): void;
};

export const Logo: React.FC<Props> = ({ onClick }) => {
  return (
    <Wrapper onClick={onClick}>
      <StyledHat />
      <StyledSmallLogo />
      <StyledLargeLogo />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  cursor: pointer;
`;

const StyledHat = styled(SantaHat)`
  display: block;
  position: absolute;
  top: 31px;
  right: 122px;

  ${media.lg} {
    display: block;
    top: 32px;
    right: 18px;
  }

  ${media.xs} {
    display: block;
  }
`;

const StyledLargeLogo = styled(LogoLarge)`
  display: block;

  ${media.lg} {
    display: none;
  }

  ${media.xs} {
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
