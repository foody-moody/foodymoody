type NotificationType =
  | 'FEED_ADDED_EVENT'
  | 'FEED_LIKED_ADDED_EVENT'
  | 'FEED_COMMENT_ADDED_EVENT'
  | 'FEED_COMMENT_LIKED_ADDED_EVENT'
  | 'FEED_COMMENT_REPLY_ADDED_EVENT'
  | 'FEED_COMMENT_REPLY_LIKED_ADDED_EVENT'
  | 'FEED_COLLECTION_ADDED_EVENT'
  | 'FEED_COLLECTION_LIKED_ADDED_EVENT'
  | 'FEED_COLLECTION_COMMENT_ADDED_EVENT'
  | 'FEED_COLLECTION_COMMENT_LIKED_ADDED_EVENT'
  | 'FEED_COLLECTION_COMMENT_REPLY_ADDED_EVENT'
  | 'FEED_COLLECTION_COMMENT_REPLY_LIKED_ADDED_EVENT'
  | 'MEMBER_MENTIONED_EVENT'
  | 'MEMBER_FOLLOWED_EVENT';

type Sender = {
  id: string;
  nickname: string;
  imageUrl: string;
};

type Target = {
  feedId: string;
  imageUrl: string;
  commentId: string;
  commentMessage: string;
};

type NotificationType =
  // 좋아요
  | 'FEED_LIKED_ADDED_EVENT'
  | 'COMMENT_LIKED_ADDED_EVENT'
  | 'FEED_COLLECTION_LIKED_ADDED_EVENT'
  // 댓글
  | 'FEED_COMMENT_ADDED_EVENT'
  | 'COLLECTION_COMMENT_ADDED_EVENT'
  // 멘션
  | 'MEMBER_MENTIONED_EVENT'
  // 팔로우
  | 'MEMBER_FOLLOWED_EVENT';

// 알림 설정
type NotiSettingKeys =
  | 'allNotification'
  | 'feedComment'
  | 'collectionComment'
  | 'feedLike'
  | 'collectionLike'
  | 'commentLike'
  | 'follow';

type NotiSettingType = Record<NotiSettingKeys, boolean>;
