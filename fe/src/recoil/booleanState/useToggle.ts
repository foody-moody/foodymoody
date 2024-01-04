import { useRecoilState } from 'recoil';
import { booleanStateFamily } from './atom';

export const useToggle = (id: string) => {
  const [isTrue, setIsTrue] = useRecoilState(booleanStateFamily(id));

  const toggleOn = () => setIsTrue(true);
  const toggleOff = () => setIsTrue(false);
  const toggle = () => setIsTrue((prev) => !prev);

  return { toggleOn, toggleOff, toggle, isTrue };
};
