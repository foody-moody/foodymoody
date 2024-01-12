type Author = {
  id: string;
  name: string;
  mood: string;
  profileImageUrl: string;
};

type CollectionItem = {
  storeMood: Badge[];
  id: string;
  thumbnailUrl: string;
  author: Author;
  title: string;
  description: string;
  followerCount: number;
  commentCount: number;
  feedCount: number;
  createdAt: string;
  updatedAt: string;
  likeCount: number;
  liked: boolean;
};
