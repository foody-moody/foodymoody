import { useMutation, useQuery } from '@tanstack/react-query';
import {
  deleteFeed,
  getFeedDetail,
  postNewFeed,
  putEditFeed,
} from 'api/feed/feed';
// import { useMemo } from 'react';
import { usePageNavigator } from 'hooks/usePageNavigator';
import { QUERY_KEY } from 'constants/queryKey';

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
