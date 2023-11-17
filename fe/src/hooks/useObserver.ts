import { useRef, useEffect, useCallback } from 'react';

type UseIntersectionObserverType = {
  inviewCallback: () => void;
  outviewCallback?: () => void;
};

export const useIntersectionObserver = ({
  inviewCallback,
  outviewCallback,
}: UseIntersectionObserverType) => {
  const observeTarget = useRef<HTMLDivElement | null>(null);

  const observerCallback = useCallback(
    (entries: IntersectionObserverEntry[]) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          inviewCallback();
          return;
        }
        outviewCallback?.();
      });
    },
    [inviewCallback, outviewCallback]
  );

  useEffect(() => {
    const observer = new IntersectionObserver(observerCallback, {
      threshold: 0.5,
    });

    if (observeTarget.current) {
      observer.observe(observeTarget.current);
    }

    return () => {
      observer.disconnect();
    };
  }, [observerCallback]);

  return { observeTarget };
};
