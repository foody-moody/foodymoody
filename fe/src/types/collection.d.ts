type Author = {
  id: string;
  name: string;
  profileImageUrl: string;
};

type AuthorWithMood = {
  mood: string;
} & Author;

type CollectionItem = {
  storeMood?: Badge[];
  moods?: Badge[];
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

type CollectionForm = {
  title: string;
  description: string;
  private: boolean;
  moodIds: string[];
};

type ProfileCollection = {
  count: number;
  author: Author;
  collections: {
    content: {
      id: string;
      title: string;
      feedCount: number;
      likeCount: number;
      commentCount: number;
      liked: boolean;
      createdAt: string;
      updatedAt: string;
      moods: Badge[];
    }[];
  };
};
