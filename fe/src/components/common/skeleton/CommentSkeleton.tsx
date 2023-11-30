import Skeleton from 'react-loading-skeleton';
import 'react-loading-skeleton/dist/skeleton.css';
import { styled } from 'styled-components';

type Props = {
  itemCount: number;
};

export const CommentSkeleton = ({ itemCount }: Props) => {
  return Array(itemCount)
    .fill(0)
    .map((_, i) => (
      <Wrapper key={i}>
        <UserImage>
          <Skeleton circle width={40} height={40} borderRadius={'50%'} />
        </UserImage>
        <TextRow>
          <Skeleton count={2} />
        </TextRow>
      </Wrapper>
    ));
};

const Wrapper = styled.li`
  display: flex;
  gap: 24px;
  width: 100%;
  margin: 16px 0;
`;

const TextRow = styled.div`
  flex: 1;
`;

const UserImage = styled.div`
  width: 40px;
  height: 40px;
`;
