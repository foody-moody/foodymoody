type NewCommentBody = {
  feedId?: string;
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
  member: CommentMemberInfo;
  hasReply: boolean;
  replyCount: number;

  // 변경사항
  likeCount: number;
  liked: boolean;
};

type CommentMemberInfo = {
  id: string;
  nickname: string;
  imageUrl: string;
};

type ReplyItemType = Omit<CommentItem, 'hasReply'>;

type EditReplyArgs = {
  feedId?: string;
  commentId?: string;
  replyId?: string;
};
