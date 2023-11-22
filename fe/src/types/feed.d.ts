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
  imageUrl: string;
  menu: MenuTag;
};

type FeedMemberInfo = {
  id: string;
  nickname: string;
  imageUrl: string;
  tasteMood: Mood;
};

type MenuTag = {
  name: string;
  rating: number;
};

type NewFeedBody = {
  location: string;
  review: string;
  storeMood: string[];
  images: {
    menu: MenuTag;
    imageUrl: string;
  }[];
};
/* TODO. 수정 예정 */
type DetailFeedProps = {
  feed: MainFeed;
};
