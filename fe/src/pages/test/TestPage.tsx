import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { HomePage } from 'pages/HomePage';
import { Logo } from 'components/common/logo/Logo';
import { TextArea } from 'components/common/textarea/Textarea';
import { UserImage } from 'components/common/userImage/UserImage';
import { UserImageEdit } from 'components/common/userImage/UserImageEdit';

export const TestPage = () => {
  const navigate = useNavigate();
  const [value, setValue] = useState<string>('');

  const onChangeValue = (value: string) => {
    setValue(value);
  };
  return (
    <PageWrapper>
      <HomePage />
      <h1>Example</h1>
      <Logo
        size="s"
        onClick={() => {
          navigate('/');
        }}
      />
      <Logo
        size="l"
        onClick={() => {
          navigate('/');
        }}
      />
      <UserImage size="s" onClick={() => {}} />
      <UserImageEdit size="m" />
      <UserImageEdit size="l" />
      <TextArea
        value={value}
        placeholder="리뷰를 입력해주세요"
        onChange={onChangeValue}
      />
    </PageWrapper>
  );
};

const PageWrapper = styled.div`
  width: 100%;
  h1 {
    font: ${({ theme: { fonts } }) => fonts.displayB24};
  }
`;
