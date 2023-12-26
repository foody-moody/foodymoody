import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { AxiosError } from 'axios';
import {
  getNotifications,
  readNotification,
} from 'service/axios/notifications/notifications';
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
  });

  const notifications =
    query.data?.pages?.flatMap((page) => page.content) || [];

  // console.log(notifications);

  return {
    ...query,
    notifications,
  };
};

export const useReadNotification = () => {
  // const toast = useToast();
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (id: string) => readNotification(id),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.notifications]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;
      console.log(errorData);

      // errorData && toast.error(errorData.message);
    },
  });
};
