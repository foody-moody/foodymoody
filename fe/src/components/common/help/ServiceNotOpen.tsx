import { styled } from 'styled-components';
import { flexColumn } from 'styles/customStyle';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { Button } from '../button/Button';

export const ServiceNotOpen = () => {
  const { navigateToHome } = usePageNavigator();

  const handleNavigateToHome = () => {
    navigateToHome();
  };
  const handleNavigateToGithub = () => {
    window.open('https://github.com/foody-moody/foodymoody', '_blank');
  };

  return (
    <Wrapper>
      <FlexColumn>
        <Text>서비스 준비중입니다.</Text>
        <SubText>푸디무디를 기대해 주세요!</SubText>
      </FlexColumn>

      <FlexColumn>
        <Button
          size="s"
          backgroundColor="orange"
          shadow
          width={250}
          onClick={handleNavigateToHome}
        >
          홈으로
        </Button>
        <Button
          size="s"
          backgroundColor="black"
          shadow
          width={250}
          onClick={handleNavigateToGithub}
        >
          깃허브 둘러보기
        </Button>
      </FlexColumn>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 300px;
  height: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 36px;
  border-radius: 4px;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  background: ${({ theme: { colors } }) => colors.white};
`;

const FlexColumn = styled.div`
  ${flexColumn}
  justify-content: center;
  align-items: center;
  gap: 8px;
`;

const Text = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayB16};
  color: ${({ theme: { colors } }) => colors.black};
`;

const SubText = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;
