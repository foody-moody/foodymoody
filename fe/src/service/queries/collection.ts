import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useMemo } from 'react';
import { useNavigate } from 'react-router-dom';
import { useToast } from 'recoil/toast/useToast';
import {
  addUserCollection,
  getAllCollections,
  getDetailCollection,
  getUserCollectionTitle,
  getProfileCollections,
  getCollectionsWithFeedStatus,
  addFeedToCollection,
  deleteFeedToCollection,
  deleteCollection,
  editCollection,
} from 'service/axios/collection/collection';
import { QUERY_KEY } from 'service/constants/queryKey';
import { PATH } from 'constants/path';

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

export const useGetUserCollectionTitle = (type: string) => {
  return useQuery({
    queryKey: [QUERY_KEY.myCollections],
    queryFn: () => getUserCollectionTitle(),
    enabled: type === 'default',
  });
};

export const useGetCollectionsWithFeedStatus = (feedId: string | null) => {
  return useQuery({
    queryKey: [QUERY_KEY.myCollectionsContainFeed],
    queryFn: () => getCollectionsWithFeedStatus(feedId),
    enabled: !!feedId,
  });
};

type AddAndDeleteFeedToCollectionArgs = {
  collectionId: string;
  feedId: string;
};

export const useAddFeedToCollection = () => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: ({ collectionId, feedId }: AddAndDeleteFeedToCollectionArgs) =>
      addFeedToCollection(collectionId, feedId),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.myCollectionsContainFeed]);
      queryClient.invalidateQueries([QUERY_KEY.collectionDetail]);
      // queryClient.invalidateQueries([QUERY_KEY.myCollectionsContainFeed]);
      toast.success('해당 피드를 컬렉션에 추가했어요');
    },
    onError: (error) => {
      console.error('Error adding feed to collection:', error);
      toast.error('오류가 발생해 피드를 추가할 수 없어요');
    },
  });
};

export const useDeleteFeedFromCollection = () => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: ({ collectionId, feedId }: AddAndDeleteFeedToCollectionArgs) =>
      deleteFeedToCollection(collectionId, feedId),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.myCollectionsContainFeed]);
      queryClient.invalidateQueries([QUERY_KEY.collectionDetail]);
      toast.success('해당 피드를 컬렉션에서 제거했어요');
    },
    onError: (error) => {
      console.error('Error deleting feed from collection:', error);
      toast.error('오류가 발생해 피드를 제거할 수 없어요');
    },
  });
};

export const useAddUserCollection = () => {
  const toast = useToast();
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (collectionForm: CollectionForm) =>
      addUserCollection(collectionForm),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.profileCollections]);
    },
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
        return lastPage.collections.last
          ? undefined
          : lastPage.collections.number + 1;
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
};
export const useGetCollectionDetail = (id: string) => {
  return useQuery({
    queryKey: [QUERY_KEY.collectionDetail, id],
    queryFn: () => getDetailCollection(id),
    enabled: !!id,
  });
};

export const useEditCollection = (id: string) => {
  const queryClient = useQueryClient();
  const toast = useToast();

  return useMutation({
    mutationFn: (contents: {
      title: string;
      content: string;
      moodIds?: string[];
    }) => editCollection(id, contents),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.collectionDetail, id]);
      toast.success('컬렉션이 수정 되었습니다.');
    },

    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;
      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteCollection = (id: string) => {
  const queryClient = useQueryClient();
  const navigate = useNavigate();
  const toast = useToast();

  return useMutation({
    mutationFn: () => deleteCollection(id),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.collections]);

      toast.success('컬렉션이 삭제되었습니다.');
      navigate(PATH.COLLECTION, { replace: true });
    },

    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;
      errorData && toast.error(errorData.message);
    },
  });
};
