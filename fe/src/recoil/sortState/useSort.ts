import { useRecoilState } from 'recoil';
import { sortStateFamily } from './atom';

export const useSort = (id: string) => {
  // TODO sort 사용처 Type정의
  const [sortBy, setSortBy] = useRecoilState(sortStateFamily(id));

  const handleSort: React.ChangeEventHandler<HTMLSelectElement> = (e) => {
    setSortBy(e.target.value);
  };

  return { sortBy, handleSort };
};
