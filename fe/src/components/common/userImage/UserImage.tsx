import { styled } from 'styled-components';
import { Spinner } from '../loading/spinner';

type UserImageProps = {
  size?: 's' | 'm' | 'l';
  imageUrl: string;
  isLoading?: boolean;
  onClick?(): void;
};

export const UserImage: React.FC<UserImageProps> = ({
  size = 's',
  imageUrl,
  isLoading = false,
  onClick,
}) => {
  //이미지 경로는 유효함, 그러나 그 이미지 경로가 요청이 안되는 것일때는?

  // const onErrorImage = (
  //   event: React.SyntheticEvent<HTMLImageElement, Event>
  // ) => {
  //   return (event.currentTarget.src = imageUrl); // 에러이미지로 변경
  // };

  return (
    <>
      <Img
        onClick={onClick}
        $size={size}
        src={imageUrl}
        alt="유저이미지"
        // onError={onErrorImage}
      />
      {isLoading && (
        <SpinnerContainer>
          <Spinner isLoading={isLoading} />
        </SpinnerContainer>
      )}
    </>
  );
};

const Img = styled.img<{
  $size: 's' | 'm' | 'l';
}>`
  width: ${({ $size }) =>
    $size === 's' ? '40px' : $size === 'm' ? '60px' : '100%'};
  height: ${({ $size }) =>
    $size === 's' ? '40px' : $size === 'm' ? '60px' : '100%'};
  aspect-ratio: 1/1;
  vertical-align: top;

  transition: all 0.3s ease-in-out;
  object-fit: cover;
  border-radius: ${({ theme: { radius } }) => radius.half};
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  cursor: ${({ $size }) => ($size === 'l' ? 'default' : 'pointer')};
`;

const SpinnerContainer = styled.div`
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  border-radius: ${({ theme: { radius } }) => radius.half};
  background: rgba(0, 0, 0, 0.3);
`;
