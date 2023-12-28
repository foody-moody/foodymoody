import { useRecoilState } from 'recoil';
import { booleanState } from './atom';

export const useSetLayout = () => {
  const [isOn, setIsOn] = useRecoilState(booleanState);

  const handleSetOn = () => {
    setIsOn(true);
  };

  const handleSetOff = () => {
    setIsOn(false);
  };

  return { handleSetOn, handleSetOff, isOn };
};
