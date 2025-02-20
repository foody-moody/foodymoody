import { useRef, useEffect, useCallback } from 'react';

type UseObserverType = {
  callbackFn: () => void;
  rootRef?: React.RefObject<HTMLElement>;
};

export const useIntersectionObserver = ({
  callbackFn,
  rootRef,
}: UseObserverType) => {
  const observeTarget = useRef(null);

  const observerCallback = useCallback(
    (entries: IntersectionObserverEntry[]) => {
      entries.forEach((entry) => {
        entry.isIntersecting && callbackFn();
      });
    },
    [callbackFn]
  );

  useEffect(() => {
    const observer = new IntersectionObserver(observerCallback, {
      root: rootRef?.current,
      threshold: 0.5,
    });

    observeTarget.current && observer.observe(observeTarget.current);

    return () => {
      observer.disconnect();
    };
  }, [observerCallback, rootRef]);

  return { observeTarget };
};
