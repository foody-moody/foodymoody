type MainFeed = {
  id: string;
  member: FeedMemberInfo;
  createdAt: string;
  updatedAt: string;
  review: string;
  storeMood: Badge[];
  images: FeedImage[];
  likeCount: number;
  liked: boolean;
  commentCount: number;
  store: Store;
};

type FeedImage = {
  id: string;
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

type ProfileFeed = {
  id: string;
  imageUrl: string;
};

type FeedMemberInfo = {
  id: string;
  nickname: string;
  profileImageUrl: string;
  tasteMood: Badge;
};

type NewFeedBody = {
  storeId: string; //location이었음
  review: string;
  storeMoodIds: string[];
  images: {
    imageId: string;
    menu: MenuTag;
  }[];
};

type Store = {
  id: string;
  name: string | null; // TODO null은 들어갈 수 없음 수정요청
};
