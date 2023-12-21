import { useRegister } from 'service/queries/auth';
import { useGetTasteMood } from 'service/queries/mood';
import { styled } from 'styled-components';
import { Spinner } from 'components/common/loading/spinner';
import { ValidatedInput } from 'components/validatedInput/ValidatedInput';
import { useRegisterForm } from 'hooks/useRegisterForm/useRegisterForm';
import { RegisterSchemaType } from 'hooks/useRegisterForm/useRegisterFormSchema';
import { Button } from '../common/button/Button';
import { ArrowDownIcon } from '../common/icon/icons';

export const Register: React.FC = () => {
  const { register, handleSubmit, state, errorItem } = useRegisterForm();
  const { mutate: resisterMutate } = useRegister();
  const { data: tastes } = useGetTasteMood();

  const submitBtnDisabled =
    state.isValidating ||
    state.isSubmitting ||
    !state.isDirty ||
    !state.isValid;

  const onSubmit = async (value: RegisterSchemaType) => {
    const registerData = {
      email: value.email,
      nickname: value.nickname,
      password: value.password,
      reconfirmPassword: value.reconfirmPassword,
      tasteMoodId: value.tasteMoodId,
    };

    resisterMutate(registerData, {
      onError: (error) => {
        if (error.response?.data.code === 'm003') {
          errorItem.setError(
            'nickname',
            {
              type: 'duplicate',
              message: error.response?.data.message,
            },
            { shouldFocus: true }
          );
        }
        if (error.response?.data.code === 'm002') {
          errorItem.setError(
            'email',
            {
              type: 'duplicate',
              message: error.response?.data.message,
            },
            { shouldFocus: true }
          );
        }
        if (error.response?.data.code === 'm004') {
          errorItem.setError(
            'reconfirmPassword',
            {
              type: 'validate',
              message: error.response?.data.message,
            },
            { shouldFocus: true }
          );
        }
      },
    });
  };

  return (
    <Wrapper>
      <Form onSubmit={handleSubmit(onSubmit)}>
        <ValidatedInput
          {...register('email')}
          placeholder="이메일"
          helperText={errorItem.errors.email?.message}
        />
        <ValidatedInput
          {...register('nickname')}
          placeholder="닉네임"
          helperText={errorItem.errors.nickname?.message}
        />
        <ValidatedInput
          {...register('password')}
          type="password"
          placeholder="비밀번호"
          helperText={errorItem.errors.password?.message}
        />
        <ValidatedInput
          {...register('reconfirmPassword')}
          type="password"
          placeholder="비밀번호 확인"
          helperText={errorItem.errors.reconfirmPassword?.message}
        />
        <SelectLabel>
          <Select {...register('tasteMoodId')}>
            <Option value="" disabled={true}>
              무디를 선택해주세요!
            </Option>
            {tastes &&
              tastes?.map((taste: Mood) => (
                <Option key={taste.id} value={taste.id}>
                  {taste.name}
                </Option>
              ))}
          </Select>
          <ArrowDownIcon />
        </SelectLabel>
        <Button
          type="submit"
          size="l"
          backgroundColor="orange"
          disabled={submitBtnDisabled}
        >
          회원가입
          <Spinner isLoading={state.isSubmitting} color="black" />
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
  align-items: center;
  flex-direction: column;
  gap: 16px;
`;

const SelectLabel = styled.label`
  position: relative;
  width: 240px;

  svg {
    position: absolute;
    right: 8px;
    top: 50%;
    transform: translateY(-50%);
  }
`;

const Select = styled.select`
  -webkit-appearance: none;
  padding: 8px 40px 8px 12px;
  width: 240px;
  border: 1px solid ${({ theme: { colors } }) => colors.black};

  border-radius: 5px;
  background: ${({ theme: { colors } }) => colors.white};
  cursor: pointer;
  font: ${({ theme: { fonts } }) => fonts.displayM12};

  &:focus {
    outline: none;
    border-color: ${({ theme: { colors } }) => colors.textTertiary};
  }
`;

const Option = styled.option`
  &[value=''][disabled] {
    display: none;
  }
`;
