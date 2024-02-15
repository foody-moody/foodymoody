import { forwardRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { HeartSmallFill } from 'components/common/icon/icons';
import { UserImage } from 'components/common/userImage/UserImage';
import { useAuthState } from 'hooks/auth/useAuth';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';
import { PATH } from 'constants/path';

type Props = {
  collection: CollectionItem | CarouselCollectionItem;
  author?: Author;
  isDragging?: boolean;
};

export const GridItem = forwardRef<HTMLLIElement, Props>(
  ({ collection, author, isDragging }, ref) => {
    const navigate = useNavigate();
    const { userInfo } = useAuthState();
    const isAuthor = userInfo?.id === (collection?.author?.id || author?.id);

    const handleNavigateToDetail = (id: string) => {
      navigate(PATH.COLLECTION + '/' + id);
    };

    const handleNavigateToProfile = (id: string) => {
      isAuthor ? navigate(PATH.PROFILE) : navigate(PATH.PROFILE + '/' + id);
      sessionStorage.setItem('profileId', id);
    };

    const handleClickGrid = () => {
      if (isDragging) {
        return;
      } else {
        handleNavigateToDetail(collection.id);
      }
    };

    return (
      <Wrapper ref={ref} onClick={handleClickGrid}>
        <ImageContainer>
          <img
            src={
              !collection.thumbnailUrl || collection.feedCount === 0
                ? generateDefaultUserImage(collection.id)
                : collection.thumbnailUrl
            }
            alt="thumbnail"
          />
          <GridFilter className="grid-filter" />
        </ImageContainer>
        <GridContent>
          <GridHeader>
            <FeedCounter>{collection.feedCount}</FeedCounter>
          </GridHeader>
          <GridInfo>
            <Title>{collection.title}</Title>
            <InfoBottom>
              <InfoLeft
                onClick={(e) => {
                  e.stopPropagation();
                  handleNavigateToProfile(
                    collection?.author?.id || (author?.id as string)
                  );
                }}
              >
                <UserImage
                  imageUrl={
                    collection?.author?.profileImageUrl ||
                    author?.profileImageUrl ||
                    generateDefaultUserImage('얌')
                  }
                />
                <UserName>{collection?.author?.name || author?.name}</UserName>
              </InfoLeft>
              <InfoRight>
                <HeartSmallFill />
                <LikeCount>{collection.likeCount}</LikeCount>
              </InfoRight>
            </InfoBottom>
          </GridInfo>
        </GridContent>
      </Wrapper>
    );
  }
);

const Wrapper = styled.li`
  position: relative;
  display: flex;
  flex-direction: column;
  cursor: pointer;
  width: 100%;
  height: 100%;

  img {
    aspect-ratio: 1/1;
    width: 100%;
    height: 100%;
    cursor: pointer;
    vertical-align: top;
  }

  &:hover .grid-filter::after {
    opacity: 0;
  }
`;

const ImageContainer = styled.div`
  width: 100%;
  height: 100%;
`;

const GridFilter = styled.div`
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;

  &::after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(rgba(0, 0, 0, 0), 60%, rgba(0, 0, 0, 0.7));
    opacity: 1;
    transition: all 0.2s ease-in-out;
  }
`;

const GridContent = styled.div`
  position: absolute;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 100%;
  height: 100%;
  padding: 8px;
`;

const GridHeader = styled.div`
  display: flex;
  justify-content: flex-end;
  width: 100%;
`;

const Title = styled.div`
  font: ${({ theme: { fonts } }) => fonts.displayB14};
  color: ${({ theme: { colors } }) => colors.white};
`;

const FeedCounter = styled.div`
  width: 18px;
  height: 18px;
  display: flex;
  justify-content: center;
  align-items: center;
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  background: ${({ theme: { colors } }) => colors.white};
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  border-radius: ${({ theme: { radius } }) => radius.half};
`;

const GridInfo = styled.div`
  display: flex;
  flex-direction: column;
  gap: 8px;
`;

const InfoBottom = styled.div`
  display: flex;
  justify-content: space-between;
`;

const InfoLeft = styled.div`
  display: flex;
  gap: 8px;
  height: 40px;
  align-items: center;
`;

const InfoRight = styled.div`
  display: flex;
  gap: 2px;
  align-items: center;
`;

const UserName = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  color: ${({ theme: { colors } }) => colors.white};
  white-space: nowrap;
`;

const LikeCount = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  color: ${({ theme: { colors } }) => colors.white};
`;
