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

type CommentItem = {
  id: string;
  content: string;
  createdAt: string;
  updatedAt: string;
  member: Omit<FeedMemberInfo, 'tasteMood'>;
  hasReply: boolean;
};
