import { forwardRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { PATH } from 'constants/path';

type Props = {
  feed: ProfileFeed;
};

export const FeedProfileItem = forwardRef<HTMLLIElement, Props>(
  ({ feed }, ref) => {
    const navigate = useNavigate();

    const handleOpenDetailFeed = () => {
      navigate(PATH.PROFILE + PATH.DETAIL_FEED + '/' + feed.id, {
        state: { background: 'profileDetailFeed' },
      });
    };

    return (
      <Wrapper ref={ref} onClick={handleOpenDetailFeed}>
        <img src={feed.imageUrl} alt="프로필피드" />
      </Wrapper>
    );
  }
);

const Wrapper = styled.li`
  background-color: ${({ theme: { colors } }) => colors.white};
  width: 100%;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  cursor: pointer;

  &:hover {
    opacity: 0.7;
  }

  img {
    width: 100%;
    height: 100%;
    aspect-ratio: 1/1;
    object-fit: cover;
    vertical-align: top;
  }
`;
