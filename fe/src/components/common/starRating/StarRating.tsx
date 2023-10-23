import { useState } from 'react';
import { StarLargeFillIcon, StarLargeEmptyIcon } from '../icon/icons';

type Props = {
  onClick: (rating: number) => void;
};

export const StarRating: React.FC<Props> = ({ onClick }) => {
  const [currentRating, setCurrentRating] = useState(0);

  const handleStarClick = (index: number) => {
    setCurrentRating(index);
    onClick(index);
  };

  return (
    <>
      {[...Array(5)].map((_, i) => {
        const starIndex = i + 1;
        return starIndex <= currentRating ? (
          <StarLargeFillIcon
            key={starIndex}
            onClick={() => handleStarClick(starIndex)}
          />
        ) : (
          <StarLargeEmptyIcon
            key={starIndex}
            onClick={() => handleStarClick(starIndex)}
          />
        );
      })}
    </>
  );
};
