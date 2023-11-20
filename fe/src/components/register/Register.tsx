import React, { useState } from 'react';
import { useRegister } from 'service/queries/auth';
import { styled } from 'styled-components';
// import { useGetTasteMood } from 'queries/mood';
import { useInput } from 'hooks/useInput';
import { Button } from '../common/button/Button';
import { ArrowDownIcon } from '../common/icon/icons';
import { Input } from '../common/input/Input';

export const Register: React.FC = () => {
  const { mutate: resisterMutate } = useRegister();
  // const { data } = useGetTasteMood();
  const [selectedTaste, setSelectedTaste] = useState('');

  const {
    value: emailValue,
    handleChange: handleEmailChange,
    helperText: emailHelperText,
    isValid: isEmailValid,
  } = useInput({
    initialValue: '',
    validator: (value) =>
      /^[a-zA-Z0-9._-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$/.test(value), // 검증 로직 변경
    helperText: '올바른 이메일 형식이 아닙니다',
  });

  const {
    value: nicknameValue,
    handleChange: handleNicknameChange,
    helperText: nicknameHelperText,
    isValid: isNicknameValid,
  } = useInput({
    initialValue: '',
    validator: (value) => value.length > 1, // 검증 로직 변경
    helperText: '닉네임은 2자 이상 입력해주세요',
  });

  const {
    value: passwordValue,
    handleChange: handlePasswordChange,
    helperText: passwordHelperText,
    isValid: isPasswordValid,
  } = useInput({
    initialValue: '',
    validator: (value) => value.length > 7, // 검증 로직 변경
    helperText: '비밀번호는 8자 이상 입력해주세요',
  });

  const {
    value: confirmPasswordValue,
    handleChange: handleConfirmPasswordChange,
    helperText: confirmPasswordHelperText,
    isValid: isConfirmPasswordValid,
  } = useInput({
    initialValue: '',
    validator: (value) => value === passwordValue, // 검증 로직 변경
    helperText: '비밀번호가 일치하지 않습니다',
  });

  const handleSubmit = () => {
    const registerData = {
      email: emailValue,
      nickname: nicknameValue,
      password: passwordValue,
      reconfirmPassword: confirmPasswordValue,
      tasteMood: selectedTaste,
    };
    console.log(registerData);
    resisterMutate(registerData);
  };

  const isFormValid =
    isEmailValid &&
    isNicknameValid &&
    isPasswordValid &&
    isConfirmPasswordValid &&
    selectedTaste;

  return (
    <Wrapper>
      <Input
        variant="default"
        placeholder="이메일"
        onChangeValue={handleEmailChange}
        helperText={emailHelperText}
      />
      <Input
        variant="default"
        placeholder="닉네임"
        onChangeValue={handleNicknameChange}
        helperText={nicknameHelperText}
      />
      <Input
        type="password"
        variant="default"
        placeholder="비밀번호"
        onChangeValue={handlePasswordChange}
        helperText={passwordHelperText}
      />
      <Input
        type="password"
        variant="default"
        placeholder="비밀번호 확인"
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
      <Button
        size="l"
        backgroundColor="orange"
        onClick={handleSubmit}
        disabled={!isFormValid}
      >
        {/* status={register.status} */}
        회원가입
      </Button>
    </Wrapper>
  );
};

const MOCK_TASTES = [
  { id: 'f47ac10b-58cc-4372-a567-0e02b2c3d479', name: '어린이 입맛' },
  { id: '7e57d004-2b97-0e7a-b45f-5387367791cd', name: '스파이시 킹' },
  { id: 'e2a63f33-9e8f-40be-8c04-bb351d70d7d7', name: '으른 입맛' },
  { id: 'a5f4f3e7-f716-4f73-a5f0-825babbbbbbb', name: '디저트 킬러' },
  { id: '0f14d0ab-9604-4e80-bb3c-60082eeb8eef', name: '육식파' },
  { id: '7e414d03-7e7e-4f02-953d-3a0f049a6a37', name: '도전적인' },
  { id: '83a7363b-384d-3c6d-a166-8c4d270a37a8', name: '초식파' },
  { id: '2a9f910c-3d10-11e8-b467-0ed5f89f718b', name: '맵찔이' },
  { id: '2a9f90c0-3d10-11e8-b467-0ed5f89f71aa', name: '자칭 미식가' },
];

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
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
