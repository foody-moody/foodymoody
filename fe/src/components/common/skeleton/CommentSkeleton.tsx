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
        <Info>
          <Skeleton count={2} />
        </Info>
        {/* <FeedUserInfo></FeedUserInfo> */}
        {/* <Content>
          <Skeleton count={3} />
        </Content>
        <Carousel>
          <Skeleton height={'100%'} />
        </Carousel>
        <FeedAction>
          <Skeleton />
        </FeedAction> */}
      </Wrapper>
    ));
};

const Wrapper = styled.li`
  /* max-width: 566px;
  min-width: 340px; */
  width: 100%;
  /* background-color: ${({ theme: { colors } }) => colors.white};
  border: 1px solid ${({ theme: { colors } }) => colors.red};
  margin: 16px 0; */

  /* padding: 16px 16px 0 16px; */
  display: flex;
  gap: 24px;
`;

// const FeedUserInfo = styled.div`
//   padding: 16px 16px 0 16px;
//   display: flex;
//   gap: 16px;
// `;

const Info = styled.div`
  flex: 1;
`;

const UserImage = styled.div`
  width: 40px;
  height: 40px;
`;

// const Content = styled.div`
//   padding: 16px;
//   width: 100%;
// `;

// const Carousel = styled.div`
//   padding: 0 16px 16px 16px;
//   aspect-ratio: 1 / 1;
// `;

// const FeedAction = styled.div`
//   padding: 0 16px 16px 16px;
// `;
