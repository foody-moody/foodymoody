import { atom } from 'recoil';

export const toastListState = atom<ToastItem[]>({
  key: 'toastListState',
  default: [],
});
