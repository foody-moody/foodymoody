import { styled } from 'styled-components';

type UserImageProps = {
  variant?: 'default' | 'edit';
  imageUrl?: string;
  onClick?(): void;
};

export const UserImage: React.FC<UserImageProps> = (
  { variant = 'default', imageUrl, onClick }
) => {
  const generateDefaultImage = `https://source.boringavatars.com/beam/${imageUrl}`;
  // TODO imageUrl부분 전역으로 둔 member 아이디로 변경
  const onErrorImage = (
    event: React.SyntheticEvent<HTMLImageElement, Event>
  ) => {
    event.currentTarget.src = generateDefaultImage;
  };

  return (
    <Img
      onClick={onClick}
      $variant={variant}
      src={imageUrl || generateDefaultImage}
      alt="유저이미지"
      onError={onErrorImage}
    />
  );
};

const Img = styled.img<{
  $variant: 'default' | 'edit';
}>`
  width: ${({ $variant }) => ($variant === 'default' ? '40px' : '100%')};
  height: ${({ $variant }) => ($variant === 'default' ? '40px' : '100%')};
  transition: all 0.3s ease-in-out;
  object-fit: cover;
  border-radius: ${({ theme: { radius } }) => radius.half};
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  cursor: ${({ $variant }) => ($variant === 'default' ? 'pointer' : 'default')};
`;
