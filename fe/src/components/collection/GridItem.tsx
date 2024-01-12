import React from 'react';
import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { HeartSmallFill } from 'components/common/icon/icons';
import { UserImage } from 'components/common/userImage/UserImage';
import { useIntersectionObserver } from 'hooks/useObserver';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';
import { PATH } from 'constants/path';

type Props = {
  collections: CollectionItem[];
  hasNextPage?: boolean;
  fetchNextPage(): void;
};

export const GridItem: React.FC<Props> = ({
  collections,
  hasNextPage,
  fetchNextPage,
}) => {
  const navigate = useNavigate();
  const { observeTarget } = useIntersectionObserver({
    callbackFn: () => {
      hasNextPage && fetchNextPage();
    },
  });

  const handleNavigateToDetail = (id: string) => {
    navigate(PATH.COLLECTION + '/' + id);
  };

  const handleNavigateToProfile = (id: string) => {
    navigate(PATH.PROFILE + '/' + id);
  };

  return (
    <Wrapper>
      {collections.map((collection: CollectionItem, index: number) => {
        const isLastItem = index === collections.length - 2;

        return (
          <Grid
            key={collection.id}
            ref={isLastItem ? observeTarget : null}
            onClick={() => {
              handleNavigateToDetail(collection.id);
            }}
          >
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
                      handleNavigateToProfile(collection.author.id);
                    }}
                  >
                    <UserImage
                      imageUrl={
                        collection.author.profileImageUrl ||
                        generateDefaultUserImage('얌')
                      }
                    />
                    <UserName>{collection.author.name}</UserName>
                  </InfoLeft>
                  <InfoRight>
                    <HeartSmallFill />
                    <LikeCount>{collection.likeCount}</LikeCount>
                  </InfoRight>
                </InfoBottom>
              </GridInfo>
            </GridContent>
          </Grid>
        );
      })}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2px;

  ${media.xs} {
    grid-template-columns: repeat(2, 1fr);
  }
`;

const Grid = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  cursor: pointer;
  width: 100%;
  height: 100%;
  /* border: 1px solid ${({ theme: { colors } }) => colors.black}; */

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
  border: 1px solid red;
  min-width: fit-content;
  min-height: fit-content;
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
