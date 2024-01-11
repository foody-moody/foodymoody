import { privateApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const getNotifications = async (page = 0, size = 20) => {
  const { data } = await privateApi.get(END_POINT.notifications(), {
    params: { page, size },
  });
  return data;
};

export const getNotification = async (id: string) => {
  const { data } = await privateApi.get(END_POINT.notifications(id));
  return data;
};

// 이게 왜 get일까 ?
export const readNotification = async (id: string) => {
  const { data } = await privateApi.get(END_POINT.notifications(id));
  return data;
};

// 수정되면 추가
export const readAllNotifications = async () => {
  const { data } = await privateApi.put(
    END_POINT.notifications() + '/read-status'
  );
  return data;
};

export const deleteReadNotifications = async () => {
  const { data } = await privateApi.delete(
    END_POINT.notifications() + '/read-status'
  );
  return data;
};

// 알림 설정
export const getNotificationSettings = async () => {
  const { data } = await privateApi.get(END_POINT.notificationSettings);
  return data;
};

export const updateNotificationSettings = async (
  newSettings: NotiSettingType
) => {
  const { data } = await privateApi.put(
    END_POINT.notificationSettings,
    newSettings
  );
  return data;
};

export const updateAllNotificationSettings = async (allNotiState: {
  allow: boolean;
}) => {
  const { data } = await privateApi.put(
    END_POINT.notificationSettings + '/all',
    allNotiState
  );
  return data;
};
