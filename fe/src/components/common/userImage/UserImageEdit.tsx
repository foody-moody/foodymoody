import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { EditIcon } from '../icon/icons';
import { UserImage } from './UserImage';

type UserImageEditProps = {
  imageUrl?: string;
};

export const UserImageEdit: React.FC<UserImageEditProps> = ({ imageUrl }) => {
  const randomGithubImageUrl =
    'https://avatars.githubusercontent.com/u/63034672?v=4';
  const userImage = imageUrl || randomGithubImageUrl;

  const handleEditImage = () => {};

  const isAuthor = false;

  return (
    <Wrapper>
      <UserImage variant="edit" imageUrl={userImage} />
      {isAuthor && (
        <EditBtn onClick={handleEditImage}>
          <EditIcon />
        </EditBtn>
      )}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  position: relative;
  width: 100px;
  height: 100px;

  ${media.md} {
    width: 75px;
    height: 75px;
  }
`;

const EditBtn = styled.div`
  position: absolute;
  bottom: 0;
  right: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  border-radius: ${({ theme: { radius } }) => radius.half};
  background-color: ${({ theme: { colors } }) => colors.white};
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  width: 32px;
  height: 32px;
  transition: all 0.3s ease-in-out;

  &:hover,
  &:active {
    background: ${({ theme: { colors } }) => colors.bgGray200};
  }

  ${media.md} {
    width: 24px;
    height: 24px;
  }
`;
