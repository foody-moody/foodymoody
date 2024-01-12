import { useToggle } from 'recoil/booleanState/useToggle';
import { useGetCollection } from 'service/queries/collection';
import { styled } from 'styled-components';
import { GridLayout } from './GridLayout';
import { ListLayout } from './ListLayout';

export const CollectionContainer = () => {
  const { collections, hasNextPage, fetchNextPage } = useGetCollection();
  console.log(collections);
  const grid = useToggle('grid');

  return (
    <Wrapper>
      {grid.isTrue ? (
        <GridLayout
          collections={collections}
          hasNextPage={hasNextPage}
          fetchNextPage={fetchNextPage}
        />
      ) : (
        <ListLayout
          collections={collections}
          hasNextPage={hasNextPage}
          fetchNextPage={fetchNextPage}
        />
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

// const generateDefaultImage = (imageUrl: string) =>
//   `https://source.boringavatars.com/beam/${imageUrl}?colors=FF4E50,FC913A,F9D423,EDE574,E1F5C4&square`;

// const MOCK_FEEDS = Array.from({ length: 20 }, (_, index) => ({
//   id: index + 1,
//   imageUrl: generateDefaultImage(`githubrandomProfileimageurl${index + 1}`),
// }));
