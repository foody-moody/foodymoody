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

type ProfileMemberInfoType = {
  memberId: number;
  imageUrl: string;
  nickname: string;
  email: string;
  mood: string;
  myFeeds: MyFeedsType[];
  myFeedsCount: number;
};

type MyFeedsType = {
  id: number;
  imageUrl: string;
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
