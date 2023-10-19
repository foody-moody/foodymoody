import { styled } from 'styled-components';

type UserImageProps = {
  variant?: 'default' | 'edit';
  imageUrl?: string;
  onClick?(): void;
};

export const UserImage: React.FC<UserImageProps> = (
  { variant = 'default', imageUrl, onClick }
) => {
  const randomGithubImageUrl =
    'https://avatars.githubusercontent.com/u/63034672?v=4';
  const userImage = imageUrl || randomGithubImageUrl;

  return (
    <Img
      onClick={onClick}
      $variant={variant}
      src={userImage}
      alt="유저이미지"
    />
  );
};

const Img = styled.img<{ $variant: 'default' | 'edit' }>`
  width: ${({ $variant }) => ($variant === 'default' ? '40px' : '100%')};
  height: ${({ $variant }) => ($variant === 'default' ? '40px' : '100%')};
  object-fit: cover;
  border-radius: ${({ theme: { radius } }) => radius.half};
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  cursor: ${({ $variant }) => ($variant === 'default' ? 'pointer' : 'default')};
`;
