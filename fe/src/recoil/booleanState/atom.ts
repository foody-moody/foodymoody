import { atomFamily } from 'recoil';

export const booleanStateFamily = atomFamily({
  key: 'booleanState',
  default: (id) => (id === 'tool' ? false : true),
});
