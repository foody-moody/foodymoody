import { selectorFamily, DefaultValue } from 'recoil';
import { modalListState, modalState } from './atom';

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
