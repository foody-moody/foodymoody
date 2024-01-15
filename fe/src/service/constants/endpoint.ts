export const END_POINT = {
  login: `/auth/login`,
  logout: `/auth/logout`,
  refresh: `/auth/token`, // 수정가능성
  tasteMood: `/members/taste-moods`,
  storeMood: `/feeds/store-moods`,
  collection: (id?: string) =>
    id ? `/feed_collections/${id}` : `/feed_collections`,
  feedLike: (id: string) => `/feeds/${id}/likes`,
  commentLike: (id: string) => `/comments/${id}/likes`,
  replyLike: ({ commentId, replyId }: ReplyLike) =>
    `comments/${commentId}/replies/${replyId}/likes`,
  member: (id?: string) => (id ? `/members/${id}` : `/members`),
  memberFeeds: (id?: string) => `/members/${id}/feeds`,
  password: (id?: string) => `/members/${id}/password`,
  feed: (id?: string) => (id ? `/feeds/${id}` : `/feeds`),
  comment: (id?: string) => (id ? `/comments/${id}` : `/comments`),
  reply: (id: string) => `/comments/${id}/replies`,
  imageUpload: (type: 'feed' | 'user') =>
    type === 'feed' ? `/images/feeds` : `/images/members`,
  nickName: (nickname: string) =>
    `/members/duplication-check?nickname=${nickname}`,
  notifications: (id?: string) =>
    id ? `/notifications/${id}` : `/notifications`,
  notificationSettings: `/notification/settings`,
  followings: (id?: string) => `/members/${id}/followings`,
  followers: (id?: string) => `/members/${id}/followers`,
};
