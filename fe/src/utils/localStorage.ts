export const getAccessToken = () => {
  return localStorage.getItem('accessToken');
};

export const getRefreshToken = () => {
  return localStorage.getItem('refreshToken');
};

export const getUserInfo = () => {
  const userInfo = localStorage.getItem('userInfo');
  return userInfo && JSON.parse(userInfo);
};

export const setAccessToken = (accessToken: string) => {
  localStorage.setItem('accessToken', accessToken);
};

export const setRefreshToken = (refreshToken: string) => {
  localStorage.setItem('refreshToken', refreshToken);
};

export const setUserInfo = (userInfo: string) => {
  localStorage.setItem('userInfo', userInfo);
};

export const clearLoginInfo = () => {
  localStorage.removeItem('accessToken');
  localStorage.removeItem('refreshToken');
  localStorage.removeItem('userInfo');
  localStorage.clear();
};
