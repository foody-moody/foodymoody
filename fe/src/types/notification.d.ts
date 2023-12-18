type NotificationItem = {
  notificationId: string;
  sender: Sender;
  target?: Target;
  type: NotificationType;
  createdAt: string;
  updatedAt: string;
  read: boolean;
};

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
