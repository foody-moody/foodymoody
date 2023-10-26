import { styled } from 'styled-components';
import { NotiFalse, NotiTrue } from '../icon/icons';

type Props = {
  onClick?(): void;
};

export const NotiIcon: React.FC<Props> = ({ onClick }) => {
  const isNoti = true;

  return (
    <Wrapper>
      {isNoti ? (
        <NotiTrue onClick={onClick} />
      ) : (
        <NotiFalse onClick={onClick} />
      )}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  cursor: pointer;
  width: 24px;
  height: 24px;
`;
