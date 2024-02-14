import { useParams } from 'react-router-dom';
import { useDeleteFollow, usePostFollow } from 'service/queries/follow';
import { Button } from 'components/common/button/Button';
import { useAuthState } from 'hooks/auth/useAuth';

type Props = {
  size?: 's' | 'xs';
  width?: number;
  memberId: string;
  isFollowing: boolean;
};
export const FollowListButton: React.FC<Props> = ({
  size = 's',
  width = 140,
  memberId,
  isFollowing,
}) => {
  const { id } = useParams();
  const { userInfo } = useAuthState();
  const isMyProfile = userInfo?.id === id;
  const QUERY_KEY = isMyProfile ? userInfo?.id : memberId;

  const { mutate: followMutate, isLoading: isFollowLoading } = usePostFollow(
    memberId,
    QUERY_KEY
  );
  const { mutate: unfollowMutate, isLoading: isUnFlollowLoading } =
    useDeleteFollow(memberId, QUERY_KEY);

  const handleToggleFollow = () => {
    isFollowing ? unfollowMutate() : followMutate();
  };

  return (
    <>
      <Button
        size={size}
        backgroundColor={
          (!isFollowLoading || !isUnFlollowLoading) && isFollowing
            ? 'white'
            : 'black'
        }
        width={width}
        disabled={isFollowLoading || isUnFlollowLoading}
        onClick={handleToggleFollow}
      >
        <span>{isFollowing ? '팔로잉' : '팔로우'}</span>
      </Button>
    </>
  );
};
