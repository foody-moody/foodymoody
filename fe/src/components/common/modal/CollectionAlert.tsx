import { styled } from 'styled-components';
import { Button } from '../button/Button';
import { Dim } from '../dim/Dim';
import { useModal } from './useModal';

export const CollectionAlert: React.FC<CollectionAlertProps> = ({
  title,
  onConfirm,
  deleteText = '삭제',
  closeText = '취소',
}) => {
  const { closeModal } = useModal<'collectionAlert'>();

  const handleConfirm = () => {
    if (onConfirm) {
      onConfirm();
    }
    closeModal('collectionAlert');
  };

  return (
    <>
      <Dim
        isTransparent={false}
        onClick={() => {
          closeModal('collectionAlert');
        }}
      />
      <Wrapper>
        <Title>{title}</Title>

        <ActionBox>
          <Button
            size="s"
            backgroundColor="white"
            onClick={() => closeModal('collectionAlert')}
          >
            {closeText}
          </Button>

          <Button size="s" backgroundColor="black" onClick={handleConfirm}>
            {deleteText}
          </Button>
        </ActionBox>
      </Wrapper>
    </>
  );
};

const Wrapper = styled.div`
  z-index: 100;
  width: 504px;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  margin: 0 auto;
  background-color: #fff;
  padding: 24px;
  border: 1px solid black;
`;
const ActionBox = styled.div`
  display: flex;
  gap: 16px;
`;
const Title = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayB16};
  margin-bottom: 30px;
`;
