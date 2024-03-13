import { styled } from 'styled-components';
import { UserImage } from 'components/common/userImage/UserImage';
import { FollowListButton } from 'components/follow/followButton/FollowListButton';
import { useAuthState } from 'hooks/auth/useAuth';

type Props = {
  thumbnailUrl: string;
  author: {
    profileImageUrl: string;
    name: string;
    id: string;
  };
};

export const HeroImage: React.FC<Props> = ({ thumbnailUrl, author }) => {
  const { userInfo } = useAuthState();
  const isMe = userInfo?.id === author?.id;

  return (
    <>
      <Wrapper>
        <MainThumnail
          src={
            thumbnailUrl ||
            'https://images.unsplash.com/photo-1606787366850-de6330128bfc?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'
          }
        />
        <Author>
          <UserImage imageUrl={author.profileImageUrl} />
          <UserName>{author.name}</UserName>
          {!isMe && (
            <FollowListButton
              memberId={author.id}
              isFollowing={false}
              size="xs"
              width={80}
            />
          )}
        </Author>
      </Wrapper>
    </>
  );
};

const Wrapper = styled.div`
  position: relative;
`;

const MainThumnail = styled.div<{ src: string }>`
  height: 240px;
  width: 100%;
  background-image: url(${({ src }) => src});
  position: relative;
  background-size: cover;
  background-position: center;
  overflow: hidden;

  &::after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.3);
  }
`;

const Author = styled.div`
  display: flex;
  align-items: center;
  gap: 12px;
  position: absolute;
  bottom: 16px;
  left: 16px;
`;

const UserName = styled.p`
  color: ${({ theme: { colors } }) => colors.white};
  font: ${({ theme: { fonts } }) => fonts.displayB16};
`;
