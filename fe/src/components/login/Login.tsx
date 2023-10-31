import { styled } from 'styled-components';
import { Button } from 'components/common/button/Button';
import { useLogin } from 'queries/auth';
import { useInput } from 'hooks/useInput';
import { Input } from '../common/input/Input';

export const Login: React.FC = () => {
  const login = useLogin();

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

  const handleSubmit = () => {
    const loginData = {
      email: idValue,
      password: passwordValue,
    };
    console.log(loginData);

    login.mutate(loginData);
  };

  return (
    <Wrapper>
      <Input
        variant="default"
        placeholder="아이디"
        onChangeValue={handleIdChange}
        helperText={idHelperText}
      />
      <Input
        variant="default"
        placeholder="비밀번호"
        onChangeValue={handlePasswordChange}
        helperText={passwordHelperText}
      />
      <Button size="l" backgroundColor="orange" onClick={handleSubmit}>
        로그인
      </Button>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
`;
