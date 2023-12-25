import { useDeleteFollow, usePostFollow } from 'service/queries/follow';
import { Button } from 'components/common/button/Button';
import { UserCheckedIcon, UserPlusIcon } from 'components/common/icon/icons';

// import { Spinner } from 'components/common/loading/spinner';

type Props = {
  memberId: string;
  isFollowing: boolean;
};
export const FollowButton: React.FC<Props> = ({ memberId, isFollowing }) => {
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
        size="s"
        backgroundColor={
          isFollowLoading || isUnFlollowLoading ? 'black' : 'white'
        }
        width={140}
        disabled={isFollowLoading || isUnFlollowLoading}
        onClick={handleToggleFollow}
      >
        {(!isFollowLoading || !isUnFlollowLoading) && isFollowing ? (
          <UserCheckedIcon />
        ) : (
          <UserPlusIcon />
        )}
        <span>{isFollowing ? '팔로우' : '팔로잉'}</span>
        {/* <Spinner isLoading={isFollowLoading || isUnFlollowLoading} /> 일부러 시간을 오래잡아서 스피너를 보여줄지? */}
      </Button>
    </>
  );
};
