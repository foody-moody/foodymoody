import { NotiFalse, NotiTrue } from '../icon/icons';

type Props = {
  onClick?(): void;
};

export const NotiIcon: React.FC<Props> = ({ onClick }) => {
  const isNoti = true;

  return (
    <>
      {isNoti ? (
        <NotiTrue onClick={onClick} />
      ) : (
        <NotiFalse onClick={onClick} />
      )}
    </>
  );
};
