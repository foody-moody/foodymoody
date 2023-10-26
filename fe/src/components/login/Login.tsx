import { styled } from 'styled-components';
import { TextButton } from 'components/common/Button/TextButton';
import { useInput } from 'hooks/useInput';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { Button } from '../common/Button/Button';
import { LogoXLarge } from '../common/icon/icons';
import { Input } from '../common/input/Input';

export const Login: React.FC = () => {
  const { navigateToHome, navigateToRegister } = usePageNavigator();

  const {
    value: idValue,
    handleChange: handleIdChange,
    helperText: idHelperText,
    // isValid: isIdValid,
  } = useInput({
    initialValue: '',
    validator: (value) => value.length > 0, // 검증 로직 변경
  });

  const {
    value: passwordValue,
    handleChange: handlePasswordChange,
    helperText: passwordHelperText,
    // isValid: isPasswordValid,
  } = useInput({
    initialValue: '',
    validator: (value) => value.length > 5, // 검증 로직 변경
  });

  const handleLogin = () => {
    const loginData = {
      id: idValue,
      password: passwordValue,
    };
    console.log(loginData);
  };

  return (
    <Wrapper>
      <Header>
        <LogoXLarge onClick={navigateToHome} />
      </Header>
      <ContentBody>
        <Input
          variant="default"
          placeholder="아이디"
          value={idValue}
          onChangeValue={handleIdChange}
          helperText={idHelperText}
        />
        <Input
          variant="default"
          placeholder="비밀번호"
          value={passwordValue}
          onChangeValue={handlePasswordChange}
          helperText={passwordHelperText}
        />
        <Button size="l" backgroundColor="orange" onClick={handleLogin}>
          로그인
        </Button>
      </ContentBody>

      <ButtonWrapper>
        <span>계정이 없으신가요?</span>
        <TextButton color="orange" size="m" onClick={navigateToRegister}>
          가입하기
        </TextButton>
      </ButtonWrapper>
    </Wrapper>
  );
};

const Wrapper = styled.div`
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

const ContentBody = styled.div`
  display: flex;
  flex-direction: column;
  gap: 16px;
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
