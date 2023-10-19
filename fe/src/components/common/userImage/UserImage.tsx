import { styled } from 'styled-components';

type Props = {
  size?: 's' | 'm' | 'l';
  imageUrl?: string;
  onClick?(): void;
};

export const UserImage: React.FC<Props> = (
  { size = 's', imageUrl, onClick }
) => {
  const randomGithubImageUrl =
    'https://avatars.githubusercontent.com/u/63034672?v=4';
  // TODO 교체
  const userImage = imageUrl || randomGithubImageUrl;

  const handleClick = () => {
    onClick?.();
  };

  return (
    <Img onClick={handleClick} $size={size} src={userImage} alt="유저이미지" hasClickHandler={!!onClick}/>
  );
};

const Img = styled.img<{ $size: 's' | 'm' | 'l',hasClickHandler: boolean }>`
  width: ${({ $size }) => SIZE_VARIANT[$size]};
  height: ${({ $size }) => SIZE_VARIANT[$size]};
  object-fit: cover;
  border-radius: ${({ theme: { radius } }) => radius.half};
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  cursor: ${({ hasClickHandler }) => (hasClickHandler ? 'pointer' : 'default')};
`;

const SIZE_VARIANT = {
  s: '40px',
  m: '75px',
  l: '100px',
};
