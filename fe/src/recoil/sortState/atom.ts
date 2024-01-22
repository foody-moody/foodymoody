import { atomFamily } from 'recoil';

// 다른 쪽에서도 sort를 쓸 가능성
export const sortStateFamily = atomFamily({
  key: 'sortStateFamily',
  default: 'createdAt',
});
