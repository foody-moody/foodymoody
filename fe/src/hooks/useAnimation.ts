import { useEffect, useState } from 'react';

type UseAnimationReturn = {
  shouldRender: boolean;
  handleTransitionEnd: () => void;
  animationTrigger: boolean;
};

export const useAnimation = (condition: boolean): UseAnimationReturn => {
  const [isComplete, setComplete] = useState(false);

  useEffect(() => {
    if (condition) {
      setComplete(true);
    }
  }, [condition]);

  const shouldRender = condition || isComplete;

  const animationTrigger = condition && isComplete;

  const handleTransitionEnd = () => {
    if (!condition) setComplete(false);
  };

  return { shouldRender, handleTransitionEnd, animationTrigger };
};
