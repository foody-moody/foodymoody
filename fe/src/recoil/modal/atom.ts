import { atomFamily, atom } from 'recoil';

export const modalListState = atom<ModalType[]>({
  key: 'modalListState',
  default: [],
});

export const modalState = atomFamily<Modal<ModalType>, ModalType>({
  key: 'modalState',
  default: (id: ModalType) => ({
    id,
    isOpen: false,
    props: null,
  }),
});
