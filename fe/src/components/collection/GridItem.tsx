import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { HeartSmallFill } from 'components/common/icon/icons';
import { UserImage } from 'components/common/userImage/UserImage';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';

type Props = {
  collections: any;
};

export const GridItem: React.FC<Props> = ({ collections }) => {
  return (
    <Wrapper>
      {collections.map((collection) => (
        <Grid key={collection.id}>
          <img
            src={collection.imageUrl}
            alt={collection.imageUrl}
            onClick={() => {}}
          />
          <GridFilter className="grid-filter" />
          <GridContent>
            <GridHeader>
              <FeedCounter>{7}</FeedCounter>
            </GridHeader>
            <GridInfo>
              <Title>{'겁나 맛있었던 곳10선'}</Title>
              <InfoBottom>
                <InfoLeft>
                  <UserImage imageUrl={generateDefaultUserImage('얌')} />
                  <UserName>{'산타'}</UserName>
                </InfoLeft>
                <InfoRight>
                  <HeartSmallFill />
                  <LikeCount>{11}</LikeCount>
                </InfoRight>
              </InfoBottom>
            </GridInfo>
          </GridContent>
        </Grid>
      ))}
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
  border: 1px solid ${({ theme: { colors } }) => colors.black};

  img {
    width: 100%;
    height: 100%;
    cursor: pointer;
  }

  &:hover .grid-filter::after {
    opacity: 0;
  }
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
