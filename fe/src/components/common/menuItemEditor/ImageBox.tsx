import { styled } from 'styled-components';
import { PlusGhostIcon } from '../icon/icons';

type Props = {
  imageUrl: string;
  onClick(): void;
};

export const ImageBox: React.FC<Props> = ({ imageUrl, onClick }) => {
  const githubRandomProfileUrl = 'https://picsum.photos/200';

  return (
    <ImageWrapper onClick={onClick}>
      <img src={githubRandomProfileUrl} alt="menu item image" />
      <PlusGhostIcon></PlusGhostIcon>
    </ImageWrapper>
  );
};

const ImageWrapper = styled.div`
  min-width: 95px;
  max-width: 95px;
  min-height: 95px;
  max-height: 95px;
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
    /* pointer-events: none; // Make sure the overlay doesn't block interaction */
    cursor: pointer;
  }

  img {
    width: 100%;
    height: 100%;
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
