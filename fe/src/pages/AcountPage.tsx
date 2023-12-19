import 'service/queries/profile';
import { styled } from 'styled-components';
import { Button } from 'components/common/button/Button';
import {
  FlexColumnBox,
  FlexRowBox,
} from 'components/common/feedUserInfo/FeedUserInfo';
import { useModal } from 'components/common/modal/useModal';
// import { Spinner } from 'components/common/loading/spinner';

import { usePageNavigator } from 'hooks/usePageNavigator';

export const AccountPage = () => {
  const { navigateToPassword } = usePageNavigator();

  const { openModal, closeModal } = useModal<'accountAlert'>();

  const handleDelete = () => {
    //deletemutate
  };

  const handleAlert = () => {
    const modalProps = {
      onClose: () => {
        closeModal('accountAlert');
      },

      onDelete: () => {
        handleDelete();
        closeModal('accountAlert');
      },
    };

    openModal('accountAlert', modalProps);
  };
  return (
    <Wrapper>
      <Box>
        <SectionRow>
          <Title>계정 관리</Title>
        </SectionRow>
        <Content>
          <SectionRow>
            <Row>
              <SubTitle>비밀번호 변경</SubTitle>
              <Button
                size="l"
                backgroundColor="orange"
                onClick={navigateToPassword}
              >
                비밀번호 변경
              </Button>
            </Row>
          </SectionRow>
          <SectionRow>
            <SubTitle>회원 탈퇴</SubTitle>
            <Row>
              <InfoMessage>
                계정을 삭제할 수 있어요. <br />
                계정을 삭제하면 해당 계정의 정보는 복구되지 않으므로 신중히
                진행해주세요.
              </InfoMessage>
              <Button size="l" backgroundColor="orange" onClick={handleAlert}>
                회원 탈퇴
              </Button>
              {/* 알러트 */}
            </Row>
          </SectionRow>
        </Content>
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
const InfoMessage = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM16};
  color: ${({ theme: { colors } }) => colors.textPrimary};
`;
