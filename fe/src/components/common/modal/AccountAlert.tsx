import { styled } from 'styled-components';
import { Button } from '../button/Button';
import { Dim } from '../dim/Dim';
import { useModal } from './useModal';

export const AccountAlert: React.FC<AccountAlertProps> = ({
  onDelete,
  onClose,
}) => {
  const { closeModal } = useModal<'accountAlert'>();

  return (
    <>
      <Dim
        isTransparent={true}
        onClick={() => {
          closeModal('accountAlert');
        }}
      />
      <Wrapper>
        <InfoMessage>계정을 삭제하시겠습니까?</InfoMessage>

        {onDelete && (
          <Button size="s" backgroundColor="black" onClick={onDelete}>
            확인
          </Button>
        )}

        {onClose && (
          <Button size="s" backgroundColor="black" onClick={onClose}>
            취소
          </Button>
        )}
      </Wrapper>
    </>
  );
};

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 4px;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  background: ${({ theme: { colors } }) => colors.white};
  padding: 16px;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 100;
  max-width: 375px;
  min-width: 343px;
  width: 100%;
  height: fit-content;
`;
const InfoMessage = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM16};
  color: ${({ theme: { colors } }) => colors.textPrimary};
`;
