import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { AxiosError } from 'axios';
import {
  deleteReadNotifications,
  getNotificationSettings,
  getNotifications,
  readAllNotifications,
  readNotification,
  updateAllNotificationSettings,
  updateNotificationSettings,
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

  return {
    ...query,
    notifications,
  };
};

export const useReadNotification = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (id: string) => readNotification(id),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.notifications]);
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;
      console.log(errorData);
    },
  });
};

export const useReadAllNotification = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: async () => {
      const data = queryClient.getQueryData([QUERY_KEY.notifications]) as {
        pages: { content: NotificationItem[] }[];
      };
      if (data.pages[0].content.length !== 0) {
        return readAllNotifications();
      }
    },
    onSuccess: () => {
      const data = queryClient.getQueryData([QUERY_KEY.notifications]) as {
        pages: { content: NotificationItem[] }[];
      };
      if (data.pages[0].content.length !== 0) {
        queryClient.invalidateQueries([QUERY_KEY.notifications]);
      }
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;
      console.log(errorData);
    },
  });
};

export const useDeleteReadNotification = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: async () => {
      const data = queryClient.getQueryData([QUERY_KEY.notifications]) as {
        pages: { content: NotificationItem[] }[];
      };
      if (data.pages[0].content.length !== 0) {
        return deleteReadNotifications();
      }
    },
    onSuccess: () => {
      const data = queryClient.getQueryData([QUERY_KEY.notifications]) as {
        pages: { content: NotificationItem[] }[];
      };
      if (data.pages[0].content.length !== 0) {
        queryClient.invalidateQueries([QUERY_KEY.notifications]);
      }
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;
      console.log(errorData);
    },
  });
};

// 알림 설정

export const useNotificationSettings = () => {
  return useQuery([QUERY_KEY.notificationSetting], getNotificationSettings);
};

type MutationFn<T> = (settings: T) => Promise<NotiSettingType>;
type UpdateFn<T> = (
  previousSettings: NotiSettingType,
  newSettings: T
) => NotiSettingType;

const useCommonUpdateNotificationSettings = <T>(
  mutationFn: MutationFn<T>,
  updateFn: UpdateFn<T>
) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn,
    onMutate: async (newSettings: T) => {
      const previousSettings = queryClient.getQueryData([
        QUERY_KEY.notificationSetting,
      ]) as NotiSettingType;

      queryClient.setQueryData(
        [QUERY_KEY.notificationSetting],
        updateFn(previousSettings, newSettings)
      );

      return { previousSettings };
    },
    onError: (error, _, context) => {
      console.log(error);

      if (context?.previousSettings) {
        queryClient.setQueryData(
          [QUERY_KEY.notificationSetting],
          context.previousSettings
        );
      }
    },
    onSettled: () => {
      queryClient.invalidateQueries([QUERY_KEY.notificationSetting]);
    },
  });
};

export const useUpdateNotificationSettings = () => {
  return useCommonUpdateNotificationSettings<NotiSettingType>(
    updateNotificationSettings,
    (previousSettings, newSettings) => ({ ...previousSettings, ...newSettings })
  );
};

export const useUpdateAllNotificationSettings = () => {
  return useCommonUpdateNotificationSettings<{ allow: boolean }>(
    updateAllNotificationSettings,
    (previousSettings, allNotiState) => ({
      ...previousSettings,
      allNotification: allNotiState.allow,
    })
  );
};
