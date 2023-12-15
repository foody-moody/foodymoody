import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { getNotifications } from 'service/axios/notifications/notifications';
import { QUERY_KEY } from 'service/constants/queryKey';

export const useAllNotifications = () => {
  const query = useInfiniteQuery({
    queryKey: [QUERY_KEY.notifications],
    queryFn: ({ pageParam = 0 }) => getNotifications(pageParam),
    getNextPageParam: (lastPage) => {
      // console.log(lastPage.last);

      return lastPage.last ? undefined : lastPage.number + 1;
    },
    suspense: true,
    staleTime: Infinity,
  });

  const notifications =
    query.data?.pages?.flatMap((page) => page.content) || [];

  // console.log(notifications);

  return {
    ...query,
    notifications,
  };
};
