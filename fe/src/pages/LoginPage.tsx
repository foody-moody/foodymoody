import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import { LogoXLarge } from 'components/common/icon/icons';
import { Login } from 'components/login/Login';
import { usePageNavigator } from 'hooks/usePageNavigator';

export const LoginPage = () => {
  const { navigateToHome, navigateToRegister } = usePageNavigator();

  return (
    <Wrapper>
      <ContentBody>
        <Header>
          <LogoXLarge onClick={navigateToHome} />
        </Header>
        <Login />
        <ButtonWrapper>
          <span>계정이 없으신가요?</span>
          <TextButton color="orange" size="m" onClick={navigateToRegister}>
            가입하기
          </TextButton>
        </ButtonWrapper>
      </ContentBody>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const ContentBody = styled.div`
  width: 375px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 48px;
  padding: 40px 24px;
`;

const Header = styled.div`
  display: flex;
  justify-content: center;
  cursor: pointer;
`;

const ButtonWrapper = styled.div`
  display: flex;
  justify-content: center;
  gap: 8px;

  span {
    cursor: default;
    color: ${({ theme: { colors } }) => colors.textPrimary};
    font: ${({ theme: { fonts } }) => fonts.displayM14};
  }
`;
