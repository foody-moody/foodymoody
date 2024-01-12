import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import { LogoXLarge } from 'components/common/icon/icons';
import { RegisterForm } from 'components/register/RegisterForm';
import { usePageNavigator } from 'hooks/usePageNavigator';

export const RegisterPage = () => {
  const { navigateToHome, navigateToLogin } = usePageNavigator();
  return (
    <Wrapper>
      <ContentBody>
        <Header>
          <LogoXLarge onClick={navigateToHome} />
        </Header>
        <RegisterForm />
        <ButtonWrapper>
          <span>계정이 있으신가요?</span>
          <TextButton color="orange" size="m" onClick={navigateToLogin}>
            로그인
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
