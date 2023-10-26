import React, { useState } from 'react';
import { styled } from 'styled-components';
import { TextButton } from 'components/common/Button/TextButton';
import { useInput } from 'hooks/useInput';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { Button } from '../common/Button/Button';
import { ArrowDownIcon, LogoXLarge } from '../common/icon/icons';
import { Input } from '../common/input/Input';

export const Register: React.FC = () => {
  const { navigateToHome, navigateToLogin } = usePageNavigator();
  const [selectedTaste, setSelectedTaste] = useState('');

  const {
    value: emailValue,
    handleChange: handleEmailChange,
    helperText: emailHelperText,
    // isValid: isEmailValid,
  } = useInput({
    initialValue: '',
    validator: (value) => value.length > 5, // 검증 로직 변경
    helperText: '올바른 이메일 형식이 아닙니다',
  });

  const {
    value: nicknameValue,
    handleChange: handleNicknameChange,
    helperText: nicknameHelperText,
    // isValid: isNicknameValid,
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

  const {
    value: confirmPasswordValue,
    handleChange: handleConfirmPasswordChange,
    helperText: confirmPasswordHelperText,
    // isValid: isConfirmPasswordValid,
  } = useInput({
    initialValue: '',
    validator: (value) => value.length > 5, // 검증 로직 변경
  });

  const handleRegister = () => {
    const registerData = {
      email: emailValue,
      nickname: nicknameValue,
      password: passwordValue,
      confirmPassword: confirmPasswordValue,
      taste: selectedTaste,
    };
    console.log(registerData);
  };

  return (
    <Wrapper>
      <Header>
        <LogoXLarge onClick={navigateToHome} />
      </Header>
      <ContentBody>
        <Input
          variant="default"
          placeholder="이메일"
          // value={emailValue}
          onChangeValue={handleEmailChange}
          helperText={emailHelperText}
        />
        <Input
          variant="default"
          placeholder="닉네임"
          // value={nicknameValue}
          onChangeValue={handleNicknameChange}
          helperText={nicknameHelperText}
        />
        <Input
          type="password"
          variant="default"
          placeholder="비밀번호"
          // value={passwordValue}
          onChangeValue={handlePasswordChange}
          helperText={passwordHelperText}
        />
        <Input
          type="password"
          variant="default"
          placeholder="비밀번호 확인"
          value={confirmPasswordValue}
          onChangeValue={handleConfirmPasswordChange}
          helperText={confirmPasswordHelperText}
        />
        <SelectLabel>
          <Select
            value={selectedTaste}
            onChange={(e) => setSelectedTaste(e.target.value)}
          >
            <Option value="" disabled={true}>
              무디를 선택해주세요!
            </Option>
            {MOCK_TASTES.map((taste) => (
              <Option key={taste.id} value={taste.name}>
                {taste.name}
              </Option>
            ))}
          </Select>
          <ArrowDownIcon />
        </SelectLabel>
        <Button size="l" backgroundColor="orange" onClick={handleRegister}>
          회원가입
        </Button>
      </ContentBody>

      <ButtonWrapper>
        <span>계정이 있으신가요?</span>
        <TextButton color="orange" size="m" onClick={navigateToLogin}>
          로그인
        </TextButton>
      </ButtonWrapper>
    </Wrapper>
  );
};

const MOCK_TASTES = [
  { id: 1, name: '어린이 입맛' },
  { id: 2, name: '스파이시 킹' },
  { id: 3, name: '으른 입맛' },
  { id: 4, name: '디저트 킬러' },
  { id: 5, name: '육식파' },
  { id: 6, name: '도전적인' },
  { id: 7, name: '초식파' },
  { id: 8, name: '맵찔이' },
  { id: 9, name: '자칭 미식가' },
];

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
  align-items: center;
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

const SelectLabel = styled.label`
  position: relative;

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
  /* width: 100%; */
  width: 240px;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  /* border-radius: ${({ theme: { radius } }) => radius.large}; */
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
