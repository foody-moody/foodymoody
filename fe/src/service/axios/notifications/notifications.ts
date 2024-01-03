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
// export const readAllNotifications = async (id: string) => {
//   const { data } = await privateApi.get(END_POINT.notifications(id));
//   return data;
// };