import Skeleton from 'react-loading-skeleton';
import 'react-loading-skeleton/dist/skeleton.css';
import { styled } from 'styled-components';

type Props = {
  notiCount: number;
};

export const NotiSkeleton = ({ notiCount }: Props) => {
  return Array(notiCount)
    .fill(0)
    .map((_, i) => (
      <Wrapper key={i}>
        <FeedUserInfo>
          <UserImage>
            <Skeleton circle width={60} height={60} borderRadius={'50%'} />
          </UserImage>
          <Info>
            <Skeleton count={3} />
          </Info>
        </FeedUserInfo>
      </Wrapper>
    ));
};

const Wrapper = styled.li`
  max-width: 566px;
  min-width: 340px;
  width: 100%;
  margin: 16px 0;
`;

const FeedUserInfo = styled.div`
  padding: 16px 16px 0 16px;
  display: flex;
  gap: 16px;
`;
const Info = styled.div`
  flex: 1;
`;

const UserImage = styled.div`
  width: 60px;
  height: 60px;
`;
