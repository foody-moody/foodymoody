import { useDeleteFollow, usePostFollow } from 'service/queries/follow';
import { Button } from 'components/common/button/Button';

// import { Spinner } from 'components/common/loading/spinner';

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
  const { mutate: followMutate, isLoading: isFollowLoading } =
    usePostFollow(memberId);
  const { mutate: unfollowMutate, isLoading: isUnFlollowLoading } =
    useDeleteFollow(memberId);

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
