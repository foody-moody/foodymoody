type MainFeed = {
  id: string;
  member: FeedMemberInfo;
  createdAt: string;
  updatedAt: string;
  location: string;
  review: string;
  storeMood: Badge[];
  images: FeedImage[];
  likeCount: number;
  liked: boolean;
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
/* TODO. 수정 예정 */
type DetailFeedProps = {
  feed: MainFeed;
};

type Location = {
  address_name: string;
  id: string;
  phone: string;
  place_name: string;
  place_url: string;
  road_address_name: string;
  x: string;
  y: string;
};
