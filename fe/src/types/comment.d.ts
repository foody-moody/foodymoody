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
