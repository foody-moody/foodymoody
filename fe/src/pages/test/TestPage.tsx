import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { HomePage } from 'pages/HomePage';
import { FeedAction } from 'components/common/feedAction/FeedAction';
import { Input } from 'components/common/input/Input';
import { InputCore } from 'components/common/input/InputCore';
import { Logo } from 'components/common/logo/Logo';
import { TextArea } from 'components/common/textarea/Textarea';
import { useInput } from 'hooks/useInput';


export const TestPage = () => {
  const navigate = useNavigate();
  // const [value, setValue] = useState<string>('');
  // const { value, handleChange } = useInput();
  const {
    value: idValue,
    isValid: isIdValid,
    handleChange: handleIdChange,
    helperText: idHelper,
  } = useInput({
    validator: (value: string) => {
      return value.length > 2;
    },
    helperText: '3자 이상 입력해주세요',
  });

  const {
    value: passwordValue,
    isValid: isPasswordValid,
    handleChange: handlePasswordChange,
    helperText: passwordHelper,
  } = useInput({
    validator: (value: string) => {
      return value.length > 5;
    },
    helperText: '6자 이상 입력해주세요',
  });

  // const onChangeValue = (value: string) => {
  //   setValue(value);
  // };

  return (
    <PageWrapper>
      <HomePage />
      <FlexWrapper>
        <h1>Example</h1>
        <Logo
          size="s"
          onClick={() => {
            navigate('/');
          }}
        />
        <FeedAction likeCount={12} commentCount={11} />


        <Logo
          size="l"
          onClick={() => {
            navigate('/');
          }}
        />
        {/* <TextArea
          value={value}
          placeholder="리뷰를 입력해주세요"
          // onChange={onChangeValue}
          onChange={() => {}}
        /> */}
        <FlexWrapper>
          <Input variant="ghost" />
          <Input variant="underline" />
          <Input
            variant="default"
            placeholder="아이디"
            onChange={handleIdChange}
            helperText={idHelper}
          />
          <Input
            type="password"
            placeholder="비밀번호"
            onChange={handlePasswordChange}
            variant="default"
            helperText={passwordHelper}
          />
          <Input variant="comment" />
        </FlexWrapper>
      </FlexWrapper>

    </PageWrapper>
  );
};

const PageWrapper = styled.div`
  width: 100%;
  h1 {
    font: ${({ theme: { fonts } }) => fonts.displayB24};
  }
`;

const FlexWrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 16px;
`;
