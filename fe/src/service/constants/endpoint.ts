export const END_POINT = {
  login: `/auth/login`,
  logout: `/auth/logout`,
  refresh: `/auth/refresh`, // 수정가능성
  tasteMood: `/members/taste-moods`,
  storeMood: `/feeds/store-moods`,
  feedLike: (id: string) => `/feeds/${id}/likes`,
  commentLike: (id: string) => `/comments/${id}/hearts`,
  member: (id?: string) => (id ? `/members/${id}` : `/members`),
  feed: (id?: string) => (id ? `/feeds/${id}` : `/feeds`),
  comment: (id?: string) => (id ? `/comments/${id}` : `/comments`),
  reply: (id: string) => `/comments/${id}/replies`,
};
