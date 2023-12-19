import { styled } from 'styled-components';
import { Button } from 'components/common/button/Button';
import { usePageNavigator } from 'hooks/usePageNavigator';

export const SettingPage = () => {
  const { navigateToProfileEdit, navigateToPassword, navigateToAccount } =
    usePageNavigator();

  return (
    <Wrapper>
      <Title>설정</Title>
      <SubTitle>내 FoodyMoody</SubTitle>
      <Button size="l" backgroundColor="orange" onClick={navigateToProfileEdit}>
        프로필 수정
      </Button>
      <Button size="l" backgroundColor="orange" onClick={() => {}}>
        알림 설정
      </Button>
      <SubTitle>계정 · 보안</SubTitle>
      <Button size="l" backgroundColor="orange" onClick={navigateToPassword}>
        비밀번호 변경
      </Button>
      <Button size="l" backgroundColor="orange" onClick={navigateToAccount}>
        회원 탈퇴
      </Button>
      <SubTitle>추가 설정?</SubTitle>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  height: 100vh;
  width: 200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 20px;
`;

const Title = styled.h1`
  font: ${({ theme: { fonts } }) => fonts.displayB24};
`;

const SubTitle = styled.h2`
  font: ${({ theme: { fonts } }) => fonts.displayM20};
`;
