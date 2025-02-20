export const END_POINT = {
  login: `/auth/login`,
  OAuthLogin: `/auth/oauth/google`,
  logout: `/auth/logout`,
  register: `/members`,
  refresh: `/auth/token`,
  tasteMood: `/members/taste-moods`,
  storeMood: `/feeds/store-moods`,
  password: `/members/me/password`,
  store: (id?: string, keyword?: string) =>
    id ? `/stores/${id}` : `/stores/search?query=${keyword}`,
  collection: (
    id?: string,
    sort?: string // TODO sort 타입 정의
  ) => (id ? `/feed_collections/${id}` : `/feed_collections?sort=${sort}`),
  memberCollections: (id: string, sort?: string) =>
    sort
      ? `/members/${id}/collections?sort=${sort}`
      : `/members/${id}/collections`,
  feedLike: (id: string) => `/feeds/${id}/likes`,
  collectionLike: (id: string) => `/feed_collections/${id}/likes`,
  commentLike: (commentId: string, feedId?: string) =>
    `/feed/${feedId}/comments/${commentId}/likes`,
  replyLike: ({ commentId, replyId }: ReplyLike, feedId?: string) =>
    `/feed/${feedId}/comments/${commentId}/replies/${replyId}/likes`,
  storeLike: (storeId?: string) => `/stores/${storeId}/likes`,
  member: (id?: string) => (id ? `/members/${id}` : `/members/me`),
  memberFeeds: (id?: string) => `/members/${id}/feeds`,
  feed: (id?: string) => (id ? `/feeds/${id}` : `/feeds`),
  comment: (feedId?: string, commentId?: string) =>
    commentId
      ? `/feed/${feedId}/comments/${commentId}`
      : `/feed/${feedId}/comments`,
  reply: (feedId?: string, commentId?: string, replyId?: string) =>
    replyId
      ? `/feed/${feedId}/comments/${commentId}/replies/${replyId}`
      : `/feed/${feedId}/comments/${commentId}/replies`,
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
