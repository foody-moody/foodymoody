type NewCommentBody = {
  feedId: string;
  content: string;
};

type EditCommentArgs = {
  id: string;
  body: {
    content: string;
  };
};

type CommentItemType = {
  id: string;
  content: string;
  createdAt: string;
  updatedAt: string;
  member: Omit<FeedMemberInfo, 'tasteMood'>;
  hasReply: boolean;
  replyCount: number;

  // 변경사항
  likeCount: number;
  liked: boolean;
};

type ReplyItemType = Omit<CommentItem, 'hasReply'>;
