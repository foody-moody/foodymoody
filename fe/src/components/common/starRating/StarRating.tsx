import { useState } from 'react';
import { styled } from 'styled-components';
import { StarLargeFillIcon, StarLargeEmptyIcon } from '../icon/icons';

type Props = {
  currentRating: number;
  onClick: (rating: number) => void;
};

export const StarRating: React.FC<Props> = ({ onClick, currentRating }) => {
  const [index, setIndex] = useState(currentRating);

  const handleStarClick = (rating: number) => {
    const selectedRating = index === rating ? 0 : rating;
    setIndex(selectedRating);
    onClick(selectedRating);
  };

  return (
    <Wrapper>
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
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
`;
