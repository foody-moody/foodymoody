type ProfileMemberInfo = {
  id: string;
  profileImageUrl: string;
  profileImageId: string;
  nickname: string;
  email: string;
  tasteMoodId: string;
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
