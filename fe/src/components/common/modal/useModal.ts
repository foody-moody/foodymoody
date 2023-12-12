import { useCallback } from 'react';
import { useRecoilCallback } from 'recoil';
import { modalSelector } from 'recoil/modal/selector';

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
