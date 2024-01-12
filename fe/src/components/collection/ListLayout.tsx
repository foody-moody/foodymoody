import { styled } from 'styled-components';
import { useIntersectionObserver } from 'hooks/useObserver';
import { ListItem } from './ListItem';

type Props = {
  collections: CollectionItem[];
  hasNextPage?: boolean;
  fetchNextPage(): void;
};

export const ListLayout: React.FC<Props> = ({
  collections,
  hasNextPage,
  fetchNextPage,
}) => {
  const { observeTarget } = useIntersectionObserver({
    callbackFn: () => {
      hasNextPage && fetchNextPage();
    },
  });

  return (
    <Wrapper>
      {collections.map((collection: CollectionItem, index: number) => {
        const isLastItem = index === collections.length - 2;

        return (
          <ListItem
            key={collection.id}
            collection={collection}
            ref={isLastItem ? observeTarget : null}
          />
        );
      })}
    </Wrapper>
  );
};

const Wrapper = styled.ul`
  display: flex;
  flex-direction: column;
  gap: 8px;
`;
