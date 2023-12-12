import React, { useState } from 'react';
import { useRegister } from 'service/queries/auth';
import { useGetTasteMood } from 'service/queries/mood';
import { styled } from 'styled-components';
import { Spinner } from 'components/common/loading/spinner';
import { ValidatedInput } from 'components/validatedInput/ValidatedInput';
import { useInput } from 'hooks/useInput';
import { Button } from '../common/button/Button';
import { ArrowDownIcon } from '../common/icon/icons';

export const Register: React.FC = () => {
  const { mutate: resisterMutate, isLoading } = useRegister();
  const { data: tastes } = useGetTasteMood();
  const [selectedTaste, setSelectedTaste] = useState<Mood>({
    id: '',
    name: '',
  });

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
      tasteMoodId: selectedTaste?.id,
    };
    resisterMutate(registerData);
  };

  const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedName = e.target.value;

    const selectedTaste = tastes.find(
      (taste: Mood) => taste.name === selectedName
    );
    setSelectedTaste(selectedTaste || null);
  };

  const isFormValid =
    isEmailValid &&
    isNicknameValid &&
    isPasswordValid &&
    isConfirmPasswordValid &&
    selectedTaste;

  return (
    <Wrapper>
      <ValidatedInput
        placeholder="이메일"
        onChangeValue={handleEmailChange}
        helperText={emailHelperText}
      />
      <ValidatedInput
        placeholder="닉네임"
        onChangeValue={handleNicknameChange}
        helperText={nicknameHelperText}
      />
      <ValidatedInput
        type="password"
        placeholder="비밀번호"
        onChangeValue={handlePasswordChange}
        helperText={passwordHelperText}
      />
      <ValidatedInput
        type="password"
        placeholder="비밀번호 확인"
        onChangeValue={handleConfirmPasswordChange}
        helperText={confirmPasswordHelperText}
      />
      <SelectLabel>
        <Select value={selectedTaste?.name} onChange={handleSelectChange}>
          <Option value="" disabled={true}>
            무디를 선택해주세요!
          </Option>
          {tastes &&
            tastes?.map((taste: Mood) => (
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
        회원가입
        <Spinner isLoading={isLoading} color="black" />
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
