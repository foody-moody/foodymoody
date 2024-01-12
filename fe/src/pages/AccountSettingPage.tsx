import { useDeleteAccount } from 'service/queries/account';
import 'service/queries/profile';
import { styled } from 'styled-components';
import { Button } from 'components/common/button/Button';
import {
  FlexColumnBox,
  FlexRowBox,
} from 'components/common/feedUserInfo/FeedUserInfo';
import { useModal } from 'components/common/modal/useModal';
import { useAuthState } from 'hooks/auth/useAuth';
// import { Spinner } from 'components/common/loading/spinner';

import { usePageNavigator } from 'hooks/usePageNavigator';

export const AccountSettingPage = () => {
  const { navigateToPassword } = usePageNavigator();
  const { userInfo } = useAuthState();
  const { mutate: accountMutate } = useDeleteAccount(userInfo.id);
  const { openModal, closeModal } = useModal<'accountAlert'>();

  const handleDelete = () => {
    accountMutate();
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
        <Content>
          <SectionRow>
            <Row>
              <SubTitle>비밀번호 변경</SubTitle>
              <Button
                size="l"
                backgroundColor="orange"
                onClick={navigateToPassword}
                width={180}
              >
                비밀번호 변경
              </Button>
            </Row>
          </SectionRow>
          <SectionRow>
            <SubTitle>회원 탈퇴</SubTitle>
            <InfoMessage>
              계정을 삭제할 수 있어요. <br />
              계정을 삭제하면 해당 계정의 정보는 복구되지 않으므로 신중히
              진행해주세요.
            </InfoMessage>
            <Button size="l" backgroundColor="black" onClick={handleAlert}>
              회원 탈퇴
            </Button>
            {/* 알러트 */}
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
  padding: 0 16px;
`;

const Box = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: 564px;
  width: 100%;
  gap: 56px;
`;

const Content = styled(FlexColumnBox)`
  width: 100%;
  gap: 32px;
`;

const SectionRow = styled(FlexColumnBox)`
  width: 100%;
  gap: 16px;
`;

const Row = styled(FlexRowBox)`
  width: 100%;
  align-items: center;
  gap: 8px;
  justify-content: space-between;
`;

const SubTitle = styled.h2`
  flex-shrink: 0;
  font: ${({ theme: { fonts } }) => fonts.displayB20};
`;

const InfoMessage = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  color: ${({ theme: { colors } }) => colors.textPrimary};
`;
