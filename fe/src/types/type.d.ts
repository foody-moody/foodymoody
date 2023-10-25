type FeedType = {
  id: number;
  member: FeedMemberType;
  createdAt: string;
  updatedAt: string;
  location: string;
  review: string;
  mood: string;
  images: ImageType[];
  likeCount: number;
  commentCount: number;
};

type FeedMemberType = {
  imageUrl: string;
  nickname: string;
  mood: string;
};

type FeedImageType = {
  imageUrl: string;
  menu: FeedMenuType;
};

type FeedMenuType = {
  name: string;
  numStar: number;
};

type BadgeVariantType = 'store' | 'taste';

type BadgeType = {
  id: number;
  name: string;
};
type BadgeListType = BadgeType[];
