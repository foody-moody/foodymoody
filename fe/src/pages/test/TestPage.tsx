import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { HomePage } from 'pages/HomePage';
import { TextArea } from 'components/common/textarea/Textarea';
import { FeedAction } from 'components/common/feedAction/FeedAction';

import { Logo } from 'components/common/Logo';
import { PATH } from 'constants/path';


export const TestPage = () => {
  const navigate = useNavigate();
  const [value, setValue] = useState<string>('');

  const onChangeValue = (value: string) => {
    setValue(value);
  };
  PATH;
  return (
    <PageWrapper>
      <HomePage />
      <h1>Example</h1>
      <Logo
        size="s"
        onClick={() => {
          navigate('/');
        }}
      /> */}
      <FeedAction likeCount={12} commentCount={11} />
      />
      <Logo
        size="l"
        onClick={() => {
          navigate('/');
        }}
      />
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
