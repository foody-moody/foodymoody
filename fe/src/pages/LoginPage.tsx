import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import { LogoXLarge } from 'components/common/icon/icons';
import { OAuthButton } from 'components/common/oauthButton/OAuthButton';
import { LoginForm } from 'components/login/LoginForm';
import { usePageNavigator } from 'hooks/usePageNavigator';

export const LoginPage = () => {
  const { navigateToHome, navigateToRegister } = usePageNavigator();

  return (
    <Wrapper>
      <ContentBody>
        <Header>
          <LogoXLarge onClick={navigateToHome} />
        </Header>
        <LoginForm />
        <OAuthBox>
          <Divider>
            <Line />
            <OAuthHelperText>SNS계정으로 시작하기</OAuthHelperText>
            <Line />
          </Divider>
          <OAuthButton />
        </OAuthBox>
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

const OAuthBox = styled.div`
  margin-top: -16px;
  display: flex;
  flex-direction: column;
  align-items: center;

  gap: 16px;
`;

const Divider = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  gap: 16px;
`;

const Line = styled.div`
  flex: 1;
  height: 0.4px;
  background-color: ${({ theme: { colors } }) => colors.textTertiary};
`;

const OAuthHelperText = styled.p`
  flex: 1;
  text-align: center;
  white-space: nowrap;
  color: ${({ theme: { colors } }) => colors.textSecondary};
  font: ${({ theme: { fonts } }) => fonts.displayM14};
`;
