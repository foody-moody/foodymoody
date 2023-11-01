import { useMutation } from '@tanstack/react-query';
import { deleteFeed, postNewFeed, putEditFeed } from 'api/feed/feed';
import { usePageNavigator } from 'hooks/usePageNavigator';

export const useFeedMutation = (id?: string) => {
  const { navigateToHome } = usePageNavigator();

  return useMutation({
    mutationFn: (body: NewFeedBody) =>
      id ? putEditFeed(id, body) : postNewFeed(body),
    onSuccess: () => {
      navigateToHome(); // 수정 예정
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
