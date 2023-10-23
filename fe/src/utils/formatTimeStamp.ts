export const formatTimeStamp = (createdAt?: string) => {
  if (!createdAt) return;
  const currentTime = new Date();

  const userTimezoneOffset = getUserTimezoneOffset();
  const time = new Date(new Date(createdAt).getTime() - userTimezoneOffset);

  const timeDiff = currentTime.getTime() - time.getTime();

  const diffInSeconds = Math.floor(timeDiff / 1000);
  const diffInMinutes = Math.floor(diffInSeconds / 60);
  const diffInHours = Math.floor(diffInMinutes / 60);
  const diffInDays = Math.floor(diffInHours / 24);
  const diffInMonths = Math.floor(diffInDays / 30);

  if (diffInMonths > 0) return `${diffInMonths}개월 전`;
  if (diffInDays > 0) return `${diffInDays}일 전`;
  if (diffInHours > 0) return `${diffInHours}시간 전`;
  if (diffInMinutes > 0) return `${diffInMinutes}분 전`;

  return '방금 전';
};

const getUserTimezoneOffset = () => {
  const currentDate = new Date();
  return currentDate.getTimezoneOffset() * 60000;
};
