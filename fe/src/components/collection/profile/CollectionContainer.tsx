import { useToggle } from 'recoil/booleanState/useToggle';
import { styled } from 'styled-components';
import { EmptyProfileContents } from 'components/common/help/EmptyProfileContents';
import { GridLayout } from '../GridLayout';
import { ListLayout } from '../ListLayout';

type Props = {
  collections: CollectionItem[]; // 타입 프로필용으로 변경
  author: Author;
  hasNextPage?: boolean;
  fetchNextPage(): void;
};

export const CollectionContainer = ({
  collections,
  author,
  hasNextPage,
  fetchNextPage,
}: Props) => {
  const grid = useToggle('grid');

  return (
    <Wrapper>
      {collections.length === 0 ? (
        <EmptyProfileContents text="컬렉션이" />
      ) : grid ? (
        <GridLayout
          collections={collections}
          author={author}
          hasNextPage={hasNextPage}
          fetchNextPage={fetchNextPage}
        />
      ) : (
        <ListLayout
          collections={collections}
          author={author}
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
