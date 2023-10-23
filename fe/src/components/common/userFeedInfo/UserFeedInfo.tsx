import { styled } from 'styled-components';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { formatTimeStamp } from 'utils/formatTimeStamp';
import { Badge } from '../badge/Badge';
import { DotGhostIcon, MapPinSmallIcon } from '../icon/icons';
import { UserImage } from '../userImage/UserImage';

type Props = {
  feed: FeedType;
  member: FeedMemberType;
};

export const UserFeedInfo: React.FC<Props> = ({ feed, member }) => {
  const { navigateToProfile } = usePageNavigator();

  const formattedTimeStamp = formatTimeStamp(feed.createdAt);

  const handleClickMenu = () => {};

  return (
    <Wrapper>
      <ContentLeft>
        <UserImage imageUrl={member.imageUrl} onClick={navigateToProfile} />
        <FlexColumnBox>
          <ContentHeader>
            <p>{member.nickname}</p>
            <span>{formattedTimeStamp}</span>
          </ContentHeader>

          <ContentBody>
            <MapPinSmallIcon />
            <p>{feed.location}</p>
          </ContentBody>
        </FlexColumnBox>
      </ContentLeft>

      <ContentRight>
        <Badge variant="taste" id={feed.id} name={feed.mood} />
        <DotGhostIcon onClick={handleClickMenu} />
      </ContentRight>
    </Wrapper>
  );
};

export const FlexRowBox = styled.div`
  display: flex;
`;

export const FlexColumnBox = styled.div`
  display: flex;
  flex-direction: column;
`;

const Wrapper = styled(FlexRowBox)`
  width: 100%;
  justify-content: space-between;
`;

const ContentWrapper = styled(FlexRowBox)`
  width: fit-content;
  align-items: center;
  gap: 8px;
`;

const ContentHeader = styled(ContentWrapper)`
  align-items: center;
  p {
    font: ${({ theme }) => theme.fonts.displayM16};
  }
  span {
    font: ${({ theme }) => theme.fonts.displayM12};
    color: ${({ theme }) => theme.colors.textSecondary};
  }
`;

const ContentBody = styled(ContentWrapper)`
  align-items: center;
  gap: 4px;
  p {
    font: ${({ theme }) => theme.fonts.displayM12};
    color: ${({ theme }) => theme.colors.textSecondary};
  }
`;

const ContentRight = styled(FlexRowBox)`
  align-items: flex-start;
  gap: 8px;

  svg {
    cursor: pointer;
  }
`;

const ContentLeft = styled(ContentWrapper)`
  gap: 16px;
`;
