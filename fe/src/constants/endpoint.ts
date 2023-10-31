export const END_POINT = {
  login: `/auth/login`,
  logout: `/auth/logout`,
  register: `/members`,
  unRegister: (id: string) => `/members/${id}`,
  refresh: `/auth/refresh`, // 수정가능성
};
