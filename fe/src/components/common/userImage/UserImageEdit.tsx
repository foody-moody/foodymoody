import { styled } from 'styled-components';
import { EditIcon } from '../icon/icons';
import { UserImage } from './UserImage';

type Props = {
  size?: 'm' | 'l';
  imageUrl?: string;
};

export const UserImageEdit: React.FC<Props> = ({ size = 'm', imageUrl }) => {
  const randomGithubImageUrl =
    'https://avatars.githubusercontent.com/u/63034672?v=4';
  // TODO 교체
  const userImage = imageUrl || randomGithubImageUrl;

  const handleEditImage = () => {};

  return (
    <Wrapper $size={size}>
      <UserImage size={size} imageUrl={userImage} />
      <EditBtn $size={size} onClick={handleEditImage}>
        <EditIcon />
      </EditBtn>
    </Wrapper>
  );
};

const Wrapper = styled.div<{ $size: 'm' | 'l' }>`
  position: relative;
  width: ${({ $size }) => SIZE_VARIANT[$size].wrapper};
  height: ${({ $size }) => SIZE_VARIANT[$size].wrapper};
`;

const EditBtn = styled.div<{ $size: 'm' | 'l' }>`
  position: absolute;
  bottom: 0;
  right: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  width: ${({ $size }) => SIZE_VARIANT[$size].editBtn};
  height: ${({ $size }) => SIZE_VARIANT[$size].editBtn};
  border-radius: ${({ theme: { radius } }) => radius.half};
  background-color: ${({ theme: { colors } }) => colors.white};
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  &:hover,
  &:active {
    background: ${({ theme: { colors } }) => colors.bgGray200};
  }
  transition: background 0.2s ease-in-out;
`;

const SIZE_VARIANT = {
  m: {
    wrapper: '75px',
    editBtn: '24px',
  },
  l: {
    wrapper: '100px',
    editBtn: '32px',
  },
};
