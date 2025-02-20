import { useEffect } from 'react';
import { useLogin } from 'service/queries/auth';
import { styled } from 'styled-components';
import { Button } from 'components/common/button/Button';
import { ValidatedInput } from 'components/validatedInput/ValidatedInput';
import { useLoginForm } from 'hooks/useLoginForm/useLoginForm';
import { LoginSchemaType } from 'hooks/useLoginForm/useLoginFormSchema';

export const LoginForm: React.FC = () => {
  const { mutate: loginMutate } = useLogin();

  const { register, handleSubmit, state, errorItem, reset } = useLoginForm();

  useEffect(() => {
    if (state.isSubmitSuccessful) {
      reset();
    }
  }, [state.isSubmitSuccessful]);

  const setErrorAction = (field: keyof LoginSchemaType, message: string) => {
    errorItem.setError(field, { message }, { shouldFocus: true });
  };

  const onSubmit = (value: LoginSchemaType) => {
    const registerData = {
      email: value.email,
      password: value.password,
    };

    loginMutate(registerData, {
      onError: (error) => {
        switch (error.response?.data.code) {
          case 'm001':
            setErrorAction('email', error.response?.data.message);
            break;
          case 'a005':
            setErrorAction('password', error.response?.data.message);
            break;
          default:
            // TODO 추가처리
            break;
        }
      },
    });
  };

  return (
    <Wrapper>
      <Form onSubmit={handleSubmit(onSubmit)}>
        <ValidatedInput
          {...register('email')}
          placeholder="아이디"
          helperText={errorItem.errors.email?.message}
        />
        <ValidatedInput
          {...register('password')}
          type="password"
          placeholder="비밀번호"
          helperText={errorItem.errors.password?.message}
        />
        <Button
          type="submit"
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
