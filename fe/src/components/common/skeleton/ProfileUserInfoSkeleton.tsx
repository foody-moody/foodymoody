import Skeleton from 'react-loading-skeleton';
import 'react-loading-skeleton/dist/skeleton.css';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';

export const ProfileUserInfoSkeleton = () => {
  return (
    <Wrapper>
      <ContentLeft>
        <UserImage>
          <Skeleton circle width={60} height={60} borderRadius={'50%'} />
        </UserImage>
        <Column>
          <Skeleton />
          <ContentBody>
            <InfoItem>
              <Skeleton width={60} />
            </InfoItem>
            <InfoItem>
              <Skeleton width={60} />
            </InfoItem>
            <InfoItem>
              <Skeleton width={60} />
            </InfoItem>
          </ContentBody>
        </Column>
      </ContentLeft>

      <FeedAction>
        <Skeleton />
      </FeedAction>
    </Wrapper>
  );
};

const Flex = styled.div`
  display: flex;
`;

const Column = styled(Flex)`
  flex-direction: column;
`;

const Wrapper = styled(Column)`
  width: 100%;
  gap: 16px;
`;
const UserImage = styled.div`
  width: 60px;
  height: 60px;
`;

const ContentWrapper = styled(Flex)`
  width: fit-content;
  align-items: center;
  gap: 8px;
`;

const ContentLeft = styled(Flex)`
  justify-content: center;
  align-items: center;
  height: fit-content;
  gap: 32px;

  ${media.xs} {
    gap: 16px;
  }
`;

const ContentBody = styled(Flex)`
  gap: 12px;
  width: 280px;
  justify-content: space-between;
  ${media.xs} {
    width: 206px;
  }
`;

const InfoItem = styled(ContentWrapper)`
  gap: 4px;
`;
const FeedAction = styled.div`
  padding: 16px;
`;
