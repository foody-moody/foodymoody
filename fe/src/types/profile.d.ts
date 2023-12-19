type ProfileMemberInfo = {
  id: string;
  profileImageId: string;
  profileImageUrl: string;
  nickname: string;
  email: string;
  tasteMoodId: string;
  followingCount: number;
  followerCount: number;
  following: boolean;
  followed: boolean;
  feedCount: number;
};

type MyFeeds = {
  id: string;
  imageUrl: string;
};

type ProfileImageBody = {
  profileImageId: string;
  tasteMoodId: null;
};
