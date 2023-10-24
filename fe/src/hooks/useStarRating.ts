import { useState, useEffect } from 'react';

export const useStarRating = (currentRating?: number) => {
  const [index, setIndex] = useState(currentRating || 0);

  // TODO useEffect로 초기값 감지?

  const handleStarClick = (rating: number) => {
    const selectedRating = index === rating ? 0 : rating;
    setIndex(selectedRating);
  };

  return { rate: index, handleStarClick };
};

// export const useInitialValue = (initialValue<T>, condition) => {

//   useEffect(() => {
//     if (initialValue) {
//       setInitialValue(initialValue);
//     } else {
//       setInitialValue([DEFAULT_MENU_ITEM]);
//     }
//   }
//   return { initialValue };

// };
