import { useInfiniteQuery, useMutation, useQuery } from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useMemo } from 'react';
import { useToast } from 'recoil/toast/useToast';
import {
  addUserCollection,
  getAllCollections,
  getUserCollectionTitle,
  getProfileCollections,
} from 'service/axios/collection/collection';
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

export const useUserCollectionTitle = () =>
  useQuery({
    queryKey: [QUERY_KEY.myCollections],
    queryFn: () => getUserCollectionTitle(),
  });

export const useAddUserCollection = () => {
  const toast = useToast();

  return useMutation({
    mutationFn: (collectionForm: CollectionForm) =>
      addUserCollection(collectionForm),

    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;
      errorData && toast.error(errorData.message);
    },
  });
};

export const useGetProfileCollection = (memberId: string, sortBy?: string) => {
  const { data, hasNextPage, status, isLoading, fetchNextPage } =
    useInfiniteQuery({
      queryKey: [QUERY_KEY.profileCollections, memberId],
      queryFn: ({ pageParam = 0 }) =>
        getProfileCollections(pageParam, 10, memberId, sortBy),
      getNextPageParam: (lastPage) => {
        return lastPage.last ? undefined : lastPage.number + 1;
      },
    });

  const profileCollections = useMemo(() => {
    return data?.pages.flatMap((page) => page.collections.content) ?? [];
  }, [data]);

  const count = data?.pages[0]?.count || 0;
  const author = data?.pages[0]?.author || {};

  return {
    count,
    author,
    collections: profileCollections,
    hasNextPage,
    status,
    isLoading,
    fetchNextPage,
  };
}
