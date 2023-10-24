import { styled } from 'styled-components';
import { StarLargeFillIcon, StarLargeEmptyIcon } from '../icon/icons';

type Props = {
  index: number;
  onClick: (rating: number) => void;
};

export const StarRating: React.FC<Props> = ({ index, onClick }) => {
  return (
    <Wrapper>
      {[...Array(5)].map((_, i) => {
        const starIndex = i + 1;

        return starIndex <= index ? (
          <StarLargeFillIcon
            key={starIndex}
            onClick={() => onClick(starIndex)}
          />
        ) : (
          <StarLargeEmptyIcon
            key={starIndex}
            onClick={() => onClick(starIndex)}
          />
        );
      })}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
`;
