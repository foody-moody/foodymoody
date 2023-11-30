import { useCallback } from 'react';
import { createPortal } from 'react-dom';
import {
  atomFamily,
  atom,
  selectorFamily,
  useRecoilCallback,
  useRecoilValue,
  DefaultValue,
} from 'recoil';
import { styled } from 'styled-components';
import { Button } from '../button/Button';
import { Dim } from '../dim/Dim';

/* TODO. 나중에 모달 사용할 때 분리하기 */

export type ModalType = keyof ModalPropsMap;

export type ModalPropsMap = {
  test: TestModalProps;
  test2: Test2ModalProps;
  commentAlert: CommentAlertProps;
};

export type Modal<T extends ModalType> = {
  id: T;
  isOpen: boolean;
  props: ModalPropsMap[T] | null;
};

type TestModalProps = {
  name: string;
  age: number;
};

const TestModal: React.FC<TestModalProps> = ({ name, age }) => {
  const { closeModal } = useModal<'test'>();

  return (
    <div>
      Name: {name}, Age: {age}
      <button onClick={() => closeModal('test')}>Close</button>
    </div>
  );
};

type Test2ModalProps = {
  title: string;
  content: string;
};

const Test2Modal: React.FC<Test2ModalProps> = ({ title, content }) => {
  const { closeModal } = useModal<'test2'>();

  return (
    <div>
      Title: {title}, Content: {content}
      <button onClick={() => closeModal('test2')}>Close</button>
    </div>
  );
};

// type AlertModalProps = {
//   children: React.ReactNode;
// };

// export const AlertContainer: React.FC<AlertModalProps> = ({ children }) => {
//   return <div>{children}

//   </div>;
// };

type CommentAlertProps = {
  onEdit?(): void;
  onDelete?(): void;
  onClose?(): void;
  onReport?(): void;
};

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

const MODAL_COMPONENTS: {
  [key in ModalType]: React.ComponentType<ModalPropsMap[key]>;
} = {
  commentAlert: CommentAlert,
  test: TestModal,
  test2: Test2Modal,
};

export const modalListState = atom<ModalType[]>({
  key: 'modalListState',
  default: [],
});

const modalState = atomFamily<Modal<ModalType>, ModalType>({
  key: 'modalState',
  default: (id: ModalType) => ({
    id,
    isOpen: false,
    props: null,
  }),
});

export const modalSelector = selectorFamily<Modal<ModalType>, ModalType>({
  key: 'modalSelector',
  get:
    (id: ModalType) =>
    ({ get }) =>
      get(modalState(id)),
  set:
    (id: ModalType) =>
    ({ get, set, reset }, newValue) => {
      if (newValue instanceof DefaultValue) {
        set(modalListState, (prev) => prev.filter((modalId) => modalId !== id));
        reset(modalState(id));
        return;
      }

      set(modalState(id), newValue);

      if (!get(modalListState).includes(newValue.id)) {
        set(modalListState, (prev) => [...prev, newValue.id]);
      }
    },
});

export const ModalItem: React.FC<{ id: ModalType }> = ({ id }) => {
  const modalData = useRecoilValue(modalSelector(id));
  if (!modalData.isOpen || !modalData.props) return null;
  const ModalComponent = MODAL_COMPONENTS[id] as React.ComponentType<
    ModalPropsMap[typeof id]
  >;
  return <ModalComponent {...modalData.props} />;
};

export const GlobalModals = () => {
  const modalList = useRecoilValue(modalListState);

  return createPortal(
    <>
      {modalList.map((id) => (
        <ModalItem key={id} id={id} />
      ))}
    </>,
    document.getElementById('modal') as HTMLElement
  );
};

export const useModal = <T extends ModalType>() => {
  const setModal = useRecoilCallback(
    ({ set }) =>
      (id: T, value: Omit<Modal<T>, 'id'>) => {
        set(modalSelector(id), { ...value, id });
      },
    []
  );

  const closeModal = useRecoilCallback(
    ({ reset }) =>
      (id: T) => {
        reset(modalSelector(id));
      },
    []
  );

  const handleOpenModal = useCallback(
    (id: T, props: ModalPropsMap[T]) => {
      const value = {
        isOpen: true,
        props,
      };
      setModal(id, value);
    },
    [setModal]
  );

  return { openModal: handleOpenModal, closeModal };
};

export const TemporaryComponent = () => {
  const { openModal, closeModal } = useModal<'test'>();

  const handleOpenTestModal = () => {
    openModal('test', {
      name: 'John',
      age: 30,
    });
  };

  return (
    <div>
      <button onClick={handleOpenTestModal}>Open Test Modal</button>
      <button onClick={() => closeModal('test')}>Close Test Modal</button>
    </div>
  );
};
