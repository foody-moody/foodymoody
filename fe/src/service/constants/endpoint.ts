export const END_POINT = {
  login: `/auth/login`,
  logout: `/auth/logout`,
  refresh: `/auth/refresh`, // 수정가능성
  tasteMood: `/members/taste-moods`,
  storeMood: `/feeds/store-moods`,
  feedLike: (id: string) => `/feeds/${id}/likes`,
  commentLike: (id: string) => `/comments/${id}/likes`,
  replyLike: ({ commentId, replyId }: ReplyLike) =>
    `comments/${commentId}/replies/${replyId}/likes`,
  member: (id?: string) => (id ? `/members/${id}` : `/members`),
  feed: (id?: string) => (id ? `/feeds/${id}` : `/feeds`),
  comment: (id?: string) => (id ? `/comments/${id}` : `/comments`),
  reply: (id: string) => `/comments/${id}/replies`,
  imageUpload: (type: 'feed' | 'user') =>
    type === 'feed' ? `/images/feeds` : `/images/members`,
  notifications: (id?: string) =>
    id ? `/notifications/${id}` : `/notifications`,
};
