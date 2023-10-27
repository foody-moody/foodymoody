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

/* TODO. 나중에 모달 사용할 때 분리하기 */

export type ModalType = keyof ModalPropsMap;

export type ModalPropsMap = {
  test: TestModalProps;
  test2: Test2ModalProps;
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

const MODAL_COMPONENTS: {
  [key in ModalType]: React.ComponentType<ModalPropsMap[key]>;
} = {
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

const ModalItem: React.FC<{ id: ModalType }> = ({ id }) => {
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

const useModal = <T extends ModalType>() => {
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
