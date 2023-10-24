import { useState, useEffect } from 'react';

export const useStarRating = (currentRating?: number) => {
  const [index, setIndex] = useState(currentRating || 0);

  // const handleStarClick = (rating: number) => {
  //   const selectedRating = index === rating ? 0 : rating;
  //   setIndex(selectedRating);
  // };
  const handleStarClick = (rating: number) => {
    const selectedRating = index === rating ? 0 : rating;
    setIndex(selectedRating);
    return selectedRating; // 변경된 값을 반환
  };

  return { rate: index, handleStarClick };
};
