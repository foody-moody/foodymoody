import { useState } from 'react';
import { StarLargeFillIcon, StarLargeEmptyIcon } from '../icon/icons';

type Props = {
  rating: number;
  onClick: (rating: number) => void;
};

export const StarRating: React.FC<Props> = ({ onClick, rating }) => {
  const [index, setIndex] = useState(rating);

  const handleStarClick = (rate: number) => {
    const currentRate = index === rate ? 0 : rate;
    setIndex(currentRate);
    onClick(currentRate);
  };

  return (
    <>
      {[...Array(5)].map((_, i) => {
        const starIndex = i + 1;

        return starIndex <= index ? (
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
