type Author = {
  id: string;
  name: string;
  profileImageUrl: string;
};

type AuthorWithMood = {
  mood: string;
} & Author;

type CollectionItem = {
  storeMood: Badge[];
  id: string;
  thumbnailUrl: string;
  author: AuthorWithMood;
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

type CarouselCollectionItem = {
  id: string;
  author: Author;
  title: string;
  thumbnailUrl: string;
  likeCount: number;
  feedCount: number;
  liked: boolean;
};
