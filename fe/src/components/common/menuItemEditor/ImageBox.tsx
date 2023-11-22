import { styled } from 'styled-components';
import { PlusGhostIcon } from '../icon/icons';

type Props = {
  imageUrl: string;
  onClick(): void;
};

export const ImageBox: React.FC<Props> = ({ imageUrl, onClick }) => {
  imageUrl = 'https://picsum.photos/200';
  // const githubRandomProfileUrl = 'https://picsum.photos/200';

  return (
    <ImageWrapper onClick={onClick}>
      {/* <img src={githubRandomProfileUrl} alt="menu item image" /> */}
      <img src={imageUrl} alt="menu item image" />
      <PlusGhostIcon />
    </ImageWrapper>
  );
};

const ImageWrapper = styled.div`
  width: 95px;
  height: 95px;
  background-color: yellow;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;

  &::after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 95px;
    height: 95px;
    background-color: rgba(0, 0, 0, 0.3);
    pointer-events: none;
    cursor: pointer;
  }

  img {
    width: 95px;
    height: 95px;
    object-fit: cover;
  }

  svg {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 10;
  }
`;
