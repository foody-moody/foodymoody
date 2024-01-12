import React from 'react';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { useIntersectionObserver } from 'hooks/useObserver';
import { GridItem } from './GridItem';

type Props = {
  collections: CollectionItem[];
  hasNextPage?: boolean;
  fetchNextPage(): void;
};

export const GridLayout: React.FC<Props> = ({
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
          <GridItem
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
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2px;

  ${media.xs} {
    grid-template-columns: repeat(2, 1fr);
  }
`;
