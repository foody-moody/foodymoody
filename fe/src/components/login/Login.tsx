import { useRef } from 'react';
import { useLogin } from 'service/queries/auth';
import { styled } from 'styled-components';
import { Button } from 'components/common/button/Button';
import { ValidatedInput } from 'components/validatedInput/ValidatedInput';
import { useInput } from 'hooks/useInput';

export const Login: React.FC = () => {
  const { mutate: loginMutate, isLoading } = useLogin();
  const passwordRef = useRef<HTMLInputElement>(null);
  const buttonRef = useRef<HTMLButtonElement>(null);
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
      <ValidatedInput
        placeholder="아이디"
        onChangeValue={handleIdChange}
        helperText={idHelperText}
        nextRef={passwordRef}
      />
      <ValidatedInput
        ref={passwordRef}
        type="password"
        placeholder="비밀번호"
        onChangeValue={handlePasswordChange}
        helperText={passwordHelperText}
        nextRef={buttonRef}
      />
      <Button
        ref={buttonRef}
        size="l"
        backgroundColor={isLoading ? 'black' : 'orange'}
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
