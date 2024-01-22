import { useInfiniteQuery } from '@tanstack/react-query';
import { useMemo } from 'react';
import { getAllCollections } from 'service/axios/collection/collection';
import { QUERY_KEY } from 'service/constants/queryKey';

export const useGetCollection = (sortBy?: string) => {
  //  TODO sortBy 타입 정의
  const { data, hasNextPage, status, isLoading, fetchNextPage } =
    useInfiniteQuery({
      queryKey: [QUERY_KEY.collections, sortBy],
      queryFn: ({ pageParam = 0 }) => getAllCollections(pageParam, 10, sortBy),
      getNextPageParam: (lastPage) => {
        return lastPage.last ? undefined : lastPage.number + 1;
      },
      // suspense: true,
    });

  const allCollections = useMemo(() => {
    return data?.pages.flatMap((page) => page.content) ?? [];
  }, [data]);

  return {
    collections: allCollections,
    hasNextPage,
    status,
    isLoading,
    fetchNextPage,
  };
};
