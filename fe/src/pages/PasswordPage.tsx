import { usePutPassword } from 'service/queries/account';
import { styled } from 'styled-components';
import { Button } from 'components/common/button/Button';
import {
  FlexColumnBox,
  FlexRowBox,
} from 'components/common/feedUserInfo/FeedUserInfo';
import { Spinner } from 'components/common/loading/spinner';
import { ValidatedInput } from 'components/validatedInput/ValidatedInput';
import { useAuthState } from 'hooks/auth/useAuth';
import { usePasswordEditForm } from 'hooks/usePasswordEditForm/usePasswordEditForm';
import { PasswordEditSchemaType } from 'hooks/usePasswordEditForm/usePasswordEditSchema';

export const PasswordPage = () => {
  const { userInfo } = useAuthState();
  const { register, handleSubmit, state, errorItem } = usePasswordEditForm();

  const { mutate: passwordMutate } = usePutPassword(userInfo.id);

  const onSubmit = async (value: PasswordEditSchemaType) => {
    const registerData = {
      oldPassword: value.password,
      newPassword: value.newPasswordCheck,
    };

    passwordMutate(registerData, {
      onError: (error) => {
        if (
          error.response?.data.code === 'a005' ||
          error.response?.data.code === 'm005'
        ) {
          errorItem.setError(
            'password',
            {
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
      <Box>
        <SectionRow>
          <Title>비밀번호 변경</Title>
        </SectionRow>
        <Form onSubmit={handleSubmit(onSubmit)}>
          <Content>
            <SectionRow>
              <SubTitle>현재 비밀번호</SubTitle>
              <Row>
                <ValidatedInput
                  {...register('password')}
                  type="password"
                  placeholder="비밀번호"
                  helperText={errorItem.errors.password?.message}
                />
              </Row>
            </SectionRow>
            <SectionRow>
              <SubTitle>새 비밀번호</SubTitle>
              <Row>
                <ValidatedInput
                  {...register('newPassword')}
                  type="password"
                  placeholder="새 비밀번호"
                  helperText={errorItem.errors.newPassword?.message}
                />
              </Row>
            </SectionRow>
            <SectionRow>
              <SubTitle>새 비밀번호 확인</SubTitle>
              <Row>
                <ValidatedInput
                  {...register('newPasswordCheck')}
                  type="password"
                  placeholder="새 비밀번호 확인"
                  helperText={errorItem.errors.newPasswordCheck?.message}
                />
              </Row>
            </SectionRow>
          </Content>
          <Button
            size="l"
            backgroundColor="orange"
            disabled={
              state.isValidating ||
              state.isSubmitting ||
              !state.isValid ||
              !state.isDirty
            }
          >
            제출
            <Spinner isLoading={state.isSubmitting} color="black" />
          </Button>
        </Form>
      </Box>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 40px;
`;

const Box = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: 564px;
  width: 100%;
  gap: 56px;
  padding: 10px;
`;

const Form = styled.form`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 56px;
`;

const Content = styled(FlexColumnBox)`
  width: 100%;
  gap: 32px;
`;

const SectionRow = styled(FlexColumnBox)`
  width: 100%;
  gap: 8px;
`;

const Row = styled(FlexRowBox)`
  width: 100%;
  align-items: center;
  gap: 8px;
`;

const Title = styled.h1`
  font: ${({ theme: { fonts } }) => fonts.displayB24};
`;
const SubTitle = styled.h2`
  font: ${({ theme: { fonts } }) => fonts.displayM20};
`;
