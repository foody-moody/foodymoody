import { useEffect } from 'react';
import { useLogin } from 'service/queries/auth';
import { styled } from 'styled-components';
import { Button } from 'components/common/button/Button';
import { ValidatedInput } from 'components/validatedInput/ValidatedInput';
import { useLoginForm } from 'hooks/useLoginForm/useLoginForm';
import { LoginSchemaType } from 'hooks/useLoginForm/useLoginFormSchema';

export const Login: React.FC = () => {
  const { mutate: loginMutate } = useLogin();
  // const passwordReff = useRef<HTMLInputElement>(null);
  // const buttonReff = useRef<HTMLButtonElement>(null);

  const { register, handleSubmit, state, errorItem, reset } = useLoginForm();

  useEffect(() => {
    if (state.isSubmitSuccessful) {
      reset();
    }
  }, [state.isSubmitSuccessful]);

  const onSubmit = (value: LoginSchemaType) => {
    const registerData = {
      email: value.email,
      password: value.password,
    };

    loginMutate(registerData);
  };

  return (
    <Wrapper>
      <Form onSubmit={handleSubmit(onSubmit)}>
        <ValidatedInput
          {...register('email')}
          placeholder="아이디"
          helperText={errorItem.errors.email?.message}
          // nextRef={passwordReff}
          // onChangeValue={handleIdChange}
        />
        <ValidatedInput
          {...register('password')}
          // ref={passwordReff}
          type="password"
          placeholder="비밀번호"
          helperText={errorItem.errors.password?.message}
          // nextRef={buttonReff}
          // onChangeValue={handlePasswordChange}
        />
        <Button
          type="submit"
          // ref={buttonReff}
          size="l"
          backgroundColor={state.isSubmitting ? 'black' : 'orange'}
        >
          로그인
        </Button>
      </Form>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
`;

const Form = styled.form`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 16px;
`;
