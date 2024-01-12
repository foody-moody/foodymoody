import { styled } from 'styled-components';
import { Button } from '../button/Button';
import { Dim } from '../dim/Dim';
import { useModal } from './useModal';

export const CommentAlert: React.FC<CommentAlertProps> = ({
  onEdit,
  onDelete,
  onClose,
  onReport,
}) => {
  const { closeModal } = useModal<'commentAlert'>();

  return (
    <>
      <Dim
        isTransparent={true}
        onClick={() => {
          closeModal('commentAlert');
        }}
      />
      <Wrapper>
        {onEdit && (
          <Button size="s" backgroundColor="black" onClick={onEdit}>
            수정
          </Button>
        )}
        {onDelete && (
          <Button size="s" backgroundColor="black" onClick={onDelete}>
            삭제
          </Button>
        )}
        {onReport && (
          <Button size="s" backgroundColor="black">
            신고
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
