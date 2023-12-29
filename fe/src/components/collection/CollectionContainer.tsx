import { useSetLayout } from 'recoil/booleanState/useSetLayout';
import { styled } from 'styled-components';
import { GridItem } from './GridItem';
import { ListItem } from './ListItem';

export const CollectionContainer = () => {
  // const { collections } = useGetCollection();
  // console.log(collections);
  const { isGrid } = useSetLayout();

  return (
    <Wrapper>
      {isGrid ? (
        <GridItem collections={MOCK_FEEDS} />
      ) : (
        <ListItem collections={MOCK_FEEDS} />
      )}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;

const generateDefaultImage = (imageUrl: string) =>
  `https://source.boringavatars.com/beam/${imageUrl}?colors=FF4E50,FC913A,F9D423,EDE574,E1F5C4&square`;

const MOCK_FEEDS = Array.from({ length: 20 }, (_, index) => ({
  id: index + 1,
  imageUrl: generateDefaultImage(`githubrandomProfileimageurl${index + 1}`),
}));
