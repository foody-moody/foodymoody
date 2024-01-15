import { useToggle } from 'recoil/booleanState/useToggle';
import { useGetCollection } from 'service/queries/collection';
import { styled } from 'styled-components';
import { GridLayout } from './GridLayout';
import { ListLayout } from './ListLayout';

export const CollectionContainer = () => {
  // TODO 프로필에 쓰이는거는 위로 올려야함
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
