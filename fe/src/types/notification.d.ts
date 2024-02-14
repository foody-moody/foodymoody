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
  '@type'?: string; // 나중에 BE에서 제거해주시면 지워야함
  feedId?: string;
  feedThumbnailUrl?: string;
  feedCommentId?: string;
  feedCommentContent?: string;
  feedReplyId: string;
  feedReplyContent: string;
  feedCollectionId?: string;
  feedCollectionTitle?: string;
  feedCollectionThumbnailUrl?: string;
  feedCollectionCommentContent?: string;
  feedCollectionCommentId?: string;
  feedCollectionReplyId?: string;
  feedCollectionReplyContent?: string;
  followed?: boolean;
};

type NotificationItem = {
  notificationId: string;
  sender: Sender;
  target: Target;
  type: NotificationType;
  createdAt: string;
  updatedAt: string;
  read: boolean;
};

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
