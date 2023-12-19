import 'service/queries/profile';
import { styled } from 'styled-components';
import { Button } from 'components/common/button/Button';
import {
  FlexColumnBox,
  FlexRowBox,
} from 'components/common/feedUserInfo/FeedUserInfo';
// import { Spinner } from 'components/common/loading/spinner';
import { ValidatedInput } from 'components/validatedInput/ValidatedInput';
import { useInput } from 'hooks/useInput';

// import { usePageNavigator } from 'hooks/usePageNavigator';

export const PasswordPage = () => {
  // const { navigateToHome, navigateToLogin } = usePageNavigator();

  const {
    value: passwordValue,
    handleChange: handlePasswordChange,
    helperText: passwordHelperText,
    isValid: isPasswordValid,
  } = useInput({
    initialValue: '',
    validator: (value) => value.length > 7, // 검증 로직 변경
    helperText: '비밀번호는',
  });

  const {
    value: NewPasswordValue,
    handleChange: handleNewPasswordChange,
    helperText: newPasswordHelperText,
    isValid: isNewPasswordValid,
  } = useInput({
    initialValue: '',
    validator: (value) => value.length > 7, // 검증 로직 변경
    helperText: '비밀번호는 8자 이상 입력해주세요',
  });

  const {
    value: confirmNewPasswordValue,
    handleChange: handleConfirmNewPasswordChange,
    helperText: confirmNewPasswordHelperText,
    isValid: isConfirmNewPasswordValid,
  } = useInput({
    initialValue: '',
    validator: (value) => value === NewPasswordValue, // 검증 로직 변경
    helperText: '비밀번호가 일치하지 않습니다',
  });

  const handleSubmit = () => {
    const registerData = {
      oldPassword: passwordValue,
      newPassword: confirmNewPasswordValue,
    };
    console.log(registerData);
    
    //mutate
  };

  const isFormValid =
    isPasswordValid && isNewPasswordValid && isConfirmNewPasswordValid;

  return (
    <Wrapper>
      <Box>
        <SectionRow>
          <Title>비밀번호 변경</Title>
        </SectionRow>
        <Content>
          <SectionRow>
            <SubTitle>현재 비밀번호</SubTitle>
            <Row>
              <ValidatedInput
                type="password"
                placeholder="비밀번호"
                onChangeValue={handlePasswordChange}
                helperText={passwordHelperText}
              />
            </Row>
          </SectionRow>
          <SectionRow>
            <SubTitle>새 비밀번호</SubTitle>
            <Row>
              <ValidatedInput
                type="password"
                placeholder="새 비밀번호"
                onChangeValue={handleNewPasswordChange}
                helperText={newPasswordHelperText}
              />
            </Row>
          </SectionRow>
          <SectionRow>
            <SubTitle>새 비밀번호 확인</SubTitle>
            <Row>
              <ValidatedInput
                type="password"
                placeholder="새 비밀번호 확인"
                onChangeValue={handleConfirmNewPasswordChange}
                helperText={confirmNewPasswordHelperText}
              />
            </Row>
          </SectionRow>
        </Content>
        <Button
          size="l"
          backgroundColor="orange"
          onClick={handleSubmit}
          disabled={!isFormValid}
        >
          제출
          {/* <Spinner isLoading={isLoading} color="black" /> */}
        </Button>
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
