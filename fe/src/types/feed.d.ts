type MainFeed = {
  id: string;
  member: FeedMemberInfo;
  createdAt: string;
  updatedAt: string;
  location: string;
  review: string;
  storeMood: Mood[];
  images: FeedImage[];
  likeCount: number;
  isLiked: boolean;
  commentCount: number;
};

type FeedImage = {
  id: string;
  // imageId?: string;
  // imageUrl?: string;
  image: ImageType;
  menu: MenuTag;
};

type ImageType = {
  id: string;
  url: string;
};

type MenuTag = {
  name: string;
  rating: number;
};

type FeedMemberInfo = {
  id: string;
  nickname: string;
  profileImageUrl: string;
  tasteMood: Mood;
};

type NewFeedBody = {
  location: string;
  review: string;
  storeMood: string[];
  images: {
    imageId: string;
    menu: MenuTag;
  }[];
};
/* TODO. 수정 예정 */
type DetailFeedProps = {
  feed: MainFeed;
};
