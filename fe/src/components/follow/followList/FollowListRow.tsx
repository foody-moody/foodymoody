import { forwardRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { FlexRowBox } from 'components/common/feedUserInfo/FeedUserInfo';
import { UserImage } from 'components/common/userImage/UserImage';
import { FollowListButton } from '../followButton/FollowListButton';
import { PATH } from 'constants/path';

type Props = {
  followListItem: FollowListItem;
  isAuthor: boolean;
};

export const FollowListRow = forwardRef<HTMLLIElement, Props>(
  ({ followListItem, isAuthor }, ref) => {
    const navigate = useNavigate();

    const handleNavigateProfile = () => {
      if (followListItem.id) {
        navigate(PATH.PROFILE + '/' + followListItem.id);
        sessionStorage.setItem('profileId', followListItem.id);
      }
    };

    return (
      <Wrapper ref={ref}>
        <Info>
          <UserImage
            imageUrl={followListItem.profileImageUrl}
            onClick={handleNavigateProfile}
          />
          <UserName onClick={handleNavigateProfile}>
            {followListItem.nickname}
          </UserName>
        </Info>
        {!isAuthor && (
          <FollowListButton
            size="xs"
            width={100}
            memberId={followListItem.id}
            isFollowing={followListItem.following}
          />
        )}
      </Wrapper>
    );
  }
);

const Wrapper = styled.li`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
`;
const Info = styled(FlexRowBox)`
  gap: 16px;
  align-items: center;
`;

const UserName = styled.div`
  color: ${({ theme: { colors } }) => colors.textPrimary};
  font: ${({ theme: { fonts } }) => fonts.displayB14};
  display: flex;
  align-items: center;
`;
