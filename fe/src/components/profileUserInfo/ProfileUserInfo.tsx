import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { FollowButton } from 'components/follow/followButton/FollowButton';
import { useAuthState } from 'hooks/auth/useAuth';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';
import { Badge } from '../common/badge/Badge';
import { Button } from '../common/button/Button';
import { CollectableAddIcon } from '../common/icon/icons';
import { UserImageEdit } from '../common/userImage/UserImageEdit';
import { PATH } from 'constants/path';

type Props = {
  member: ProfileMemberInfo;
};

const MOCK_BADGE = {
  id: '0',
  name: '도전적인',
};

export const ProfileUserInfo: React.FC<Props> = ({ member }) => {
  const navigate = useNavigate();
  const { navigateToProfileEdit } = usePageNavigator();
  const { userInfo } = useAuthState();
  const isAuthor = member.id === userInfo.id;

  const handleAddCollection = () => {};
  const handleEditProfile = () => {
    navigateToProfileEdit();
  };

  const handleOpenFollowings = () => {
    navigate(PATH.PROFILE + '/' + member.id + PATH.FOLLOWING, {
      state: { background: 'followings' },
    });
  };

  const handleOpenFollowers = () => {
    navigate(PATH.PROFILE + '/' + member.id + PATH.FOLLOWER, {
      state: { background: 'followers' },
    });
  };

  return (
    <Wrapper>
      <ContentLeft>
        <UserImageEdit
          isAuthor={isAuthor}
          imageUrl={
            member.profileImageUrl || generateDefaultUserImage(member.id)
          }
        />
        <Column>
          <ContentHeader>
            <p>{member.nickname}</p>
            {/* TODO. Badge 적용 해야함..*/}
            <Badge badge={MOCK_BADGE} variant="taste" />
          </ContentHeader>

          <ContentBody>
            <InfoItem>
              {member.feedCount}
              <span>게시물</span>
            </InfoItem>
            <InfoItem onClick={handleOpenFollowings}>
              {member.followingCount}
              <span>팔로잉</span>
            </InfoItem>
            <InfoItem onClick={handleOpenFollowers}>
              {member.followerCount}
              <span>팔로워</span>
            </InfoItem>
          </ContentBody>
        </Column>
      </ContentLeft>
      <ButtonBox>
        <Button size="s" backgroundColor="orange" onClick={handleAddCollection}>
          <CollectableAddIcon />
          <span>나만의 컬렉션 만들기</span>
        </Button>
        {isAuthor ? (
          <Button
            size="s"
            backgroundColor="white"
            width={130}
            onClick={handleEditProfile}
          >
            <span>프로필 수정</span>
          </Button>
        ) : (
          <FollowButton memberId={member.id} isFollowing={member.following} />
        )}
      </ButtonBox>
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

const ContentHeader = styled(Flex)`
  align-items: center;
  justify-content: space-between;
  p {
    font: ${({ theme }) => theme.fonts.displayM20};
  }
  span {
    font: ${({ theme }) => theme.fonts.displayM12};
    color: ${({ theme }) => theme.colors.textSecondary};
  }
`;

const ContentBody = styled(Flex)`
  gap: 16px;
  width: 280px;
  justify-content: space-between;
  ${media.xs} {
    width: 206px;
  }
`;

const ButtonBox = styled(Flex)`
  gap: 8px;
  span {
    font: ${({ theme: { fonts } }) => fonts.displayM14};
  }
  ${media.xs} {
    span {
      font: ${({ theme: { fonts } }) => fonts.displayM12};
    }
  }
`;

const InfoItem = styled(ContentWrapper)`
  gap: 4px;
  font: ${({ theme: { fonts } }) => fonts.displayB14};
  span {
    font: ${({ theme: { fonts } }) => fonts.displayM14};
    color: ${({ theme: { colors } }) => colors.textSecondary};
  }
`;
