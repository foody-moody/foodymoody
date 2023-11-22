import { useInfiniteQuery, useMutation, useQuery } from '@tanstack/react-query';
import {
  deleteFeed,
  getAllFeeds,
  getFeedDetail,
  postNewFeed,
  putEditFeed,
} from 'service/axios/feed/feed';
import { QUERY_KEY } from 'service/constants/queryKey';
import { usePageNavigator } from 'hooks/usePageNavigator';

export const useAllFeeds = () => {
  const query = useInfiniteQuery({
    queryKey: [QUERY_KEY.allFeeds],
    queryFn: ({ pageParam = 0 }) => getAllFeeds(pageParam),
    getNextPageParam: (lastPage) => {
      return lastPage.last ? undefined : lastPage.number + 1;
    },
  });

  const feeds = query.data?.pages?.reduce((acc, page) => {
    return [...acc, ...page.content];
  }, []);

  return {
    ...query,
    feeds,
  };
};

export const useFeedDetail = (id: string) =>
  useQuery({
    queryKey: [QUERY_KEY.feedDetail, id],
    queryFn: () => getFeedDetail(id),
  });

export const useFeedEditor = (id?: string) => {
  const { navigateToHome } = usePageNavigator();

  return useMutation({
    mutationFn: (body: NewFeedBody) =>
      id ? putEditFeed(id, body) : postNewFeed(body),
    onSuccess: () => {
      navigateToHome(); // 수정 예정(post시에는 home으로, put시에는 detail로)
    },
    onError: (error) => {
      console.log('put editFeed error: ', error);
    },
  });
};

export const useDeleteFeed = () => {
  const { navigateToHome } = usePageNavigator();

  return useMutation({
    mutationFn: (id: string) => deleteFeed(id),
    onSuccess: () => {
      navigateToHome();
    },
    onError: (error) => {
      console.log('delete feed error: ', error);
    },
  });
};
