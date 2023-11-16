import { styled } from 'styled-components';
import { Button } from 'components/common/button/Button';
import { useLogin } from 'queries/auth';
import { useInput } from 'hooks/useInput';
import { Input } from '../common/input/Input';

export const Login: React.FC = () => {
  const { mutate: loginMutate } = useLogin();

  const {
    value: idValue,
    handleChange: handleIdChange,
    helperText: idHelperText,
    isValid: isIdValid,
  } = useInput({
    initialValue: '',
    validator: (value) =>
      /^[a-zA-Z0-9._-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$/.test(value),
    helperText: '이메일 형식에 맞게 입력해주세요',
  });

  const {
    value: passwordValue,
    handleChange: handlePasswordChange,
    helperText: passwordHelperText,
    isValid: isPasswordValid,
  } = useInput({
    initialValue: '',
    validator: (value) => value.trim().length > 7,
    helperText: '비밀번호는 8자 이상 입력해주세요',
  });

  const handleSubmit = () => {
    const loginData = {
      email: idValue,
      password: passwordValue,
    };
    console.log(loginData);

    isIdValid && isPasswordValid && loginMutate(loginData);
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
        type="password"
        variant="default"
        placeholder="비밀번호"
        onChangeValue={handlePasswordChange}
        helperText={passwordHelperText}
      />
      <Button
        size="l"
        backgroundColor="orange"
        onClick={handleSubmit}
        disabled={!isIdValid || !isPasswordValid}
      >
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
