import { useRecoilState } from 'recoil';
import { booleanState } from './atom';

export const useSetLayout = () => {
  const [isGrid, setIsGrid] = useRecoilState(booleanState);

  const handleSetOn = () => {
    setIsGrid(true);
  };

  const handleSetOff = () => {
    setIsGrid(false);
  };

  return { handleSetOn, handleSetOff, isGrid };
};
